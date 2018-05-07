package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.Maze;

/**
 * Utility Class to load mazes from bitmap files.
 */
public final class MazeLoader {

    static Maze loadMaze(final int mazeId) {
        return new Maze(20, 20);
    }
}
