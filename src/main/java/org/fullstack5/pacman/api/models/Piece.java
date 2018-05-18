package org.fullstack5.pacman.api.models;

import lombok.Getter;
import lombok.Setter;

/**
 * A movable piece within the game.
 */
@Getter
@Setter
public final class Piece {
    public enum Type {
        PACMAN(Archetype.PACMAN),
        BLINKY(Archetype.GHOST),
        PINKY(Archetype.GHOST),
        INKY(Archetype.GHOST),
        CLYDE(Archetype.GHOST);

        private Archetype archetype;
        Type(Archetype archetype) {
            this.archetype = archetype;
        }

        public Archetype getArchetype() {
            return archetype;
        }
    }

    public enum Archetype {
        PACMAN, GHOST
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
