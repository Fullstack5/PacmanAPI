package org.fullstack5.pacmanapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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
    private Position position;
    private Direction direction;

    public Piece(@NotNull Type type, @NotNull Position position) {
        this.type = type;
        this.position = position;
    }
}
