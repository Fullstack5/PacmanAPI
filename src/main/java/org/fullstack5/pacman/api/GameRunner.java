package org.fullstack5.pacman.api;

import lombok.Getter;
import org.fullstack5.pacman.api.models.*;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * A runner to execute the game rules and logic
 */
public final class GameRunner {

    private final Game game;
    private Map<PlayerType, String> players;

    @Getter
    private final ConnectableFlux<GameState> flux;

    public GameRunner(final Game game, Duration step) {
        this.game = game;
        this.flux = Flux
                .interval(step)
                .map(t -> this.performStep())
                .takeUntil(state -> !state.getState().equals(State.IN_PROGRESS))
                .log()
                .publish();
        this.players = new HashMap();
    }

    final void start(final String gameId) {
        new PacmanGui(gameId, game.getMaze()).initialize(flux);
    }

    public Maze getMaze() {
        return game.getMaze();
    }

    public void setPlayerAuthId(String authId, PlayerType type) {
        if (players.containsKey(type)) {
            throw new IllegalArgumentException("Player was already registered");
        }
        players.put(type, authId);

        // start the flux publisher if both pacman and ghosts player are connected
        if(players.containsKey(PlayerType.PACMAN) && players.containsKey(PlayerType.GHOSTS)) {
            flux.connect();
        }
    }

    public final GameState performStep() {
        // update all pieces
        game.getPieces().stream()
                .filter(piece -> piece.isActive())
                .forEach(piece ->
                        piece.setPosition(
                                determineNewPosition(piece.getPosition(), piece.getDirection())
                        )
        );

        // update timers
        updateTimers();

        // if vulnerability ended make all ghosts deadly again
        if (game.getTicksVulnerable() <= 0) {
            game.getGhosts().forEach(ghost -> ghost.setVulnerable(false));
        }

        Piece pacman = game.getPacman();

        // detect ghost collisions
        for (Piece ghost : game.getGhosts()) {
            if (collided(pacman, ghost)) {
                if (ghost.isVulnerable()) {
                    ghost.setPosition(getSpawnPosition(ghost));
                    ghost.setTicksDisabled(5);
                    ghost.setVulnerable(false);
                } else {
                    game.setState(State.PACMAN_LOST);
                    return createState();
                }
            }
        }

        // eat pacdots
        game.getRemainingPacdots().remove(pacman.getPosition());

        // eat power pellets
        if (game.getRemainingPellets().remove(pacman.getPosition())) {
            game.setTicksVulnerable(20);
            game.getGhosts().forEach(ghost -> ghost.setVulnerable(true));
        }

        // check if all dots & pellets are eaten
        boolean allDotsEaten = game.getRemainingPacdots().isEmpty();
        boolean allPelletsEaten = game.getRemainingPellets().isEmpty();
        if (allDotsEaten && allPelletsEaten) {
            game.setState(State.PACMAN_WON);
            return createState();
        }

        return createState();
    }

    public final boolean collided(Piece pacman, Piece ghost) {
        // ended up on the same position
        if (pacman.getPosition().equals(ghost.getPosition())) {
            return true;
        }

        // crossed position
        if (pacman.getPosition().equals(ghost.getPreviousPosition()) &&
                pacman.getPreviousPosition().equals(ghost.getPosition())) {
            return true;
        }

        // no collision
        return false;
    }

    public final GameState createState() {
        return new GameState(
                game.getTime(),
                game.getState(),
                game.getRemainingPacdots(),
                game.getRemainingPellets(),
                createMovingPiece(game.getPacman()),
                createMovingPiece(game.getBlinky()),
                createMovingPiece(game.getPinky()),
                createMovingPiece(game.getInky()),
                createMovingPiece(game.getClyde())
        );
    }

    public MovingPiece createMovingPiece(Piece piece) {
        return new MovingPiece(
                piece.getPreviousPosition(),
                piece.getPosition(),
                piece.getDirection(),
                piece.isVulnerable(),
                piece.isActive()
        );
    }

    public final void setDirection(String authId, final Direction direction, final Piece.Type type) {
        boolean authIdValid = players.entrySet().stream()
                .filter(entry -> entry.getKey().controls(type))
                .anyMatch(entry -> entry.getValue().equals(authId));

        if (!authIdValid) {
            throw new IllegalArgumentException("authId is not correct");
        }

        getPiece(type).setDirection(direction);
    }

    private void updateTimers() {
        game.setTime(game.getTime() + 1);
        game.getPieces().forEach(piece -> piece.reduceTicksDisabled());
        game.reduceTicksVulnerable();
    }

    private Piece getPiece(final Piece.Type type) {
        switch (type) {
            case PACMAN:
                return game.getPacman();
            case BLINKY:
                return game.getBlinky();
            case PINKY:
                return game.getPinky();
            case INKY:
                return game.getInky();
            case CLYDE:
                return game.getClyde();
            default:
                throw new RuntimeException("getPiece called with a type that does not exist: " + type);
        }
    }

    private Position getSpawnPosition(Piece piece) {
        switch (piece.getType()) {
            case PACMAN:
                return game.getMaze().getPacmanSpawn();
            case BLINKY:
                return game.getMaze().getBlinkySpawn();
            case PINKY:
                return game.getMaze().getPinkySpawn();
            case INKY:
                return game.getMaze().getInkySpawn();
            case CLYDE:
                return game.getMaze().getClydeSpawn();
            default:
                throw new RuntimeException("getSpawnPosition called with a type that does not exist: " + piece.getType());
        }
    }

    public final Position determineNewPosition(final Position position, final Direction direction) {
        if (position == null || direction == null) {
            return position;
        }
        final Maze maze = game.getMaze();
        int x = boundedMove(position.getX(), direction.getDeltaX(), maze.getWidth());
        int y = boundedMove(position.getY(), direction.getDeltaY(), maze.getHeight());

        if (maze.isWall(x, y)) {
            return position;
        }
        return new Position(x, y);
    }

    public int boundedMove(int position, int delta, int upperBound) {
        int result = (position + delta) % upperBound;
        if (result < 0) {
            result += upperBound;
        }
        return result;
    }
}
