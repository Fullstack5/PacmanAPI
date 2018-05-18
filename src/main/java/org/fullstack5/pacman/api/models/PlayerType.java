package org.fullstack5.pacman.api.models;

public enum PlayerType {
    PACMAN(Piece.Archetype.PACMAN),
    GHOSTS(Piece.Archetype.GHOST);

    private Piece.Archetype controlledArchetype;
    PlayerType(Piece.Archetype archetype) {
        controlledArchetype = archetype;
    }

    public boolean controls(Piece.Type type) {
        return type.getArchetype().equals(controlledArchetype);
    }
}
