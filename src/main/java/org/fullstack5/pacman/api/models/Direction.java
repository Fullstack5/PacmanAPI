package org.fullstack5.pacman.api.models;

import lombok.Getter;

/**
 * Direction to move in.
 */
@Getter
public enum Direction {
    NORTH(90, 0, -1),
    EAST(0, 1, 0),
    SOUTH(270, 0, 1),
    WEST(180, -1, 0);

    private final int angle;
    private final int deltaX;
    private final int deltaY;

    Direction(final int angle, final int deltaX, final int deltaY) {
        this.angle = angle;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction random() {
        return Direction.values()[(int) (Math.random() * Direction.values().length)];
    }
}
