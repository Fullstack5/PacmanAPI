package org.fullstack5.pacman.api;

import lombok.Getter;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Game;
import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.State;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * A runner to execute the game rules and logic
 */
public final class GameRunner {

    private final Game game;
    @Getter
    private final ConnectableFlux<GameState> flux;

    public GameRunner(final Game game) {
        this.game = game;
        this.flux = Flux
                .interval(Duration.ofSeconds(1))
                .map(t -> this.performStep())
                .publish();
    }

    final void start(final String gameId) {
        flux.connect();
        new PacmanGui(gameId, game.getMaze()).initialize(flux);
    }

    public final GameState performStep() {
        // update time
        long time = game.getTime();
        game.setTime(time + 1);

        // update all pieces
        game.getPieces().forEach(piece ->
                piece.setPosition(determineNewPosition(piece.getPosition(), piece.getDirection()))
        );

        Piece pacman = game.getPacman();

        // detect ghost collisions
        if (game.getGhosts().stream()
                .anyMatch(ghost -> collided(pacman, ghost))) {
            game.setState(State.PACMAN_LOST);
            flux.connect().dispose();
        }

        // eat pacdots
        game.getRemainingPacdots().remove(pacman.getPosition());

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
                State.IN_PROGRESS,
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
                piece.isVulnerable()
        );
    }

    public final void setDirection(final Direction direction, final Piece.Type type) {
        getPiece(type).setDirection(direction);
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
