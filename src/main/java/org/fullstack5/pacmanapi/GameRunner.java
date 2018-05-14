package org.fullstack5.pacmanapi;

import lombok.Getter;
import org.fullstack5.pacmanapi.models.*;
import org.fullstack5.pacmanapi.models.response.GameState;
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

    final void start() {
        flux.connect();
        new PacmanGui(game.getMaze()).initialize(flux);
    }

    public final GameState performStep() {
        // update time
        long time = game.getTime();
        game.setTime(time + 1);

        // update all pieces
        game.getPieces().forEach(piece ->
                piece.setPosition(determineNewPosition(piece.getPosition(), piece.getDirection()))
        );

        return getState();
    }

    public final GameState getState() {
        return new GameState(
                game.getTime(),
                game.getRemainingPacdots(),
                game.getRemainingPellets(),
                game.getPacman(),
                game.getBlinky(),
                game.getPinky(),
                game.getInky(),
                game.getClyde()
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
        return new Position(x, y);
    }

    private int boundedMove(int position, int delta, int upperBound) {
        int result = (position + delta) % upperBound;
        if (result < 0) {
            result += upperBound;
        }
        return result;
    }
}
