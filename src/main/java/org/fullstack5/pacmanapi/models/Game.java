package org.fullstack5.pacmanapi.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Representation of a game within the server.
 */
@Getter
@Setter
public final class Game {

    private final Maze maze;
    private long time;
    private Piece pacman;

    public Game(final Maze maze) {
        this.maze = maze;
    }

    public Position newPosition(Position position, Direction direction) {
        if (position == null) {
            return null;
        }
        if (direction == null) {
            return position;
        }

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
