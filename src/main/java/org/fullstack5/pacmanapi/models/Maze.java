package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The representation of the maze within the game.
 */
@Getter
@AllArgsConstructor
public final class Maze {
    private final int width;
    private final int height;
}
