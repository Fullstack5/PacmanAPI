package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * The representation of the maze within the game.
 */
@Getter
@AllArgsConstructor
public final class Maze {
    private final boolean[][] walls;
    private final List<Position> dots;
    private final List<Position> powerPellets;
    private final Position pacmanSpawn;
    private final Position blinkySpawn;
    private final Position pinkySpawn;
    private final Position inkySpawn;
    private final Position clydeSpawn;

    public final int getWidth() {
        return walls.length;
    }

    public final int getHeight() {
        return walls[0].length;
    }

    public final boolean isWall(final int x, final int y) {
        return walls[x][y];
    }
}
