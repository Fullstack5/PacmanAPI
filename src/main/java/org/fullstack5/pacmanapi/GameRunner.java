package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.*;
import org.fullstack5.pacmanapi.models.response.GameState;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * A runner to execute the game rules and logic
 */
public class GameRunner {

    final Game game;
    final ConnectableFlux<GameState> flux;

    public GameRunner(Game game) {
        this.game = game;
        this.flux = Flux
                .interval(Duration.ofSeconds(1))
                .map(t -> this.step())
                .publish();
        flux.connect();
    }

    public ConnectableFlux<GameState> getFlux() {
        return this.flux;
    }

    public GameState step() {

        // update time
        long time = game.getTime();
        time = time + 1;
        game.setTime(time);

        // update pacman
        Piece pacman = game.getPacman();
        pacman.setPosition(newPosition(pacman.getPosition(), pacman.getDirection()));

        return new GameState(
                time,
                pacman.getPosition()
        );
    }

    public void setMove(Direction direction) {
        this.game.getPacman().setDirection(direction);
    }

    public Position newPosition(Position position, Direction direction) {
        if (position == null) {
            return null;
        }
        if (direction == null) {
            return position;
        }

        Maze maze = game.getMaze();
        int y, x;
        switch (direction) {
            case NORTH:
                y = position.getY() - 1;
                if (y < 0) {
                    y = maze.getWidth() - 1;
                }
                return new Position(position.getX(), y);
            case SOUTH:
                y = position.getY() + 1;
                if (y >= maze.getWidth()) {
                    y = 0;
                }
                return new Position(position.getX(), y);
            case WEST:
                x = position.getX() - 1;
                if (x < 0) {
                    x = maze.getHeight() - 1;
                }
                return new Position(x, position.getY());
            case EAST:
                x = position.getX() + 1;
                if (x >= maze.getHeight()) {
                    x = 0;
                }
                return new Position(x, position.getY());
            default:
                return position;
        }
    }
}
