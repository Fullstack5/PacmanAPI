package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A movable piece within the game.
 */
@Getter
@Setter
public final class Piece {
    public enum Type {
        PACMAN, BLINKY, PINKY, INKY, CLYDE
    }

    private final Type type;
    private Position previousPosition;
    private Position position;
    private Direction direction;
    private boolean isVulnerable;

    public Piece(final Type type, final Position position) {
        this(type, position, Direction.WEST);
    }

    public Piece(final Type type, final Position position, final Direction direction) {
        this.type = type;
        this.position = position;
        this.direction = direction;
        this.isVulnerable = false;
    }

    public void setPosition(Position position) {
        this.previousPosition = this.position;
        this.position = position;
    }
}
