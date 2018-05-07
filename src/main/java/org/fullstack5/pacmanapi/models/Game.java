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

}
