package org.fullstack5.pacman.api.models;

import java.util.Arrays;
import java.util.List;

import static org.fullstack5.pacman.api.models.Piece.Type.*;

public enum PlayerType {
    PACMAN(Piece.Type.PACMAN),
    GHOSTS(BLINKY, PINKY, INKY, CLYDE);

    private List<Piece.Type> controlledPieces;
    PlayerType(Piece.Type... pieces) {
        controlledPieces = Arrays.asList(pieces);
    }

    public boolean controls(Piece.Type type) {
        return controlledPieces.contains(type);
    }
}
