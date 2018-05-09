package org.fullstack5.pacmanapi.models;

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
    private final int xInc;
    private final int yInc;

    private Direction(final int angle, final int xInc, final int yInc) {
        this.angle = angle;
        this.xInc = xInc;
        this.yInc = yInc;
    }

    public static Direction random() {
        return Direction.values()[(int) (Math.random() * Direction.values().length)];
    }
}
