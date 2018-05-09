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
        flux.connect();
    }

    public final GameState performStep() {
        // update time
        long time = game.getTime();
        game.setTime(time + 1);


        // update all pieces
        game.getPieces().stream()
                .forEach(piece ->
                        piece.setPosition(determineNewPosition(piece.getPosition(), piece.getDirection()))
                );

        return getState();
    }

    public final GameState getState() {
        return new GameState(
                game.getTime(),
                game.getRemainingPacdots(),
                game.getRemainingPellets(),
                game.getPacman().getPosition(),
                game.getBlinky().getPosition(),
                game.getPinky().getPosition(),
                game.getInky().getPosition(),
                game.getClyde().getPosition()
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
        return new Position(
                forceBetween(position.getX() + direction.getXInc(), 0, maze.getWidth() - 1),
                forceBetween(position.getY() + direction.getYInc(), 0, maze.getHeight() - 1));
    }

    /**
     * Ensure min <= value <= max
     */
    private int forceBetween(final int value, final int min, final int max) {
        return Math.max(Math.min(value, max), min);
    }
}
