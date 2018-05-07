package org.fullstack5.pacmanapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private Maze maze;
    private long time;
    private Piece pacman;


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
