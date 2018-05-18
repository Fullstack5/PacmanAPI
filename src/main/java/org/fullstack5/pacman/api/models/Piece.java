package org.fullstack5.pacman.api.models;

import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    private Position previousPosition;
    private Position position;
    private Direction direction;
    private boolean isVulnerable;

    @Getter(AccessLevel.NONE)
    private int ticksDisabled;

    public Piece(final Type type, final Position position) {
        this(type, position, 1);
    }

    public Piece(final Type type, final Position position, int ticksDisabled) {
        this(type, position, Direction.WEST, ticksDisabled);
    }

    public Piece(final Type type, final Position position, final Direction direction, int ticksDisabled) {
        this.type = type;
        this.position = position;
        this.direction = direction;
        this.ticksDisabled = ticksDisabled;
        this.isVulnerable = false;
    }

    public void setPosition(Position position) {
        this.previousPosition = this.position;
        this.position = position;
    }

    public void reduceTicksDisabled() {
        if (this.ticksDisabled > 0) {
            this.ticksDisabled -= 1;
        }
    }

    public boolean isActive() {
        return this.ticksDisabled <= 0;
    }
}
