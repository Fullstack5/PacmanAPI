package org.fullstack5.pacman.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The representation of the maze within the game.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class Maze {
    private boolean[][] walls;
    private List<Position> dots;
    private List<Position> powerPellets;
    private Position pacmanSpawn;
    private Position blinkySpawn;
    private Position pinkySpawn;
    private Position inkySpawn;
    private Position clydeSpawn;

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
