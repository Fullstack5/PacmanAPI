package org.fullstack5.pacmanapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a game within the server.
 */
@Getter
@Setter
public final class Game {

    private final Maze maze;
    private long time;
    private final List<Piece> pieces;
    private final Piece pacman;
    private final Piece blinky;
    private final Piece pinky;
    private final Piece inky;
    private final Piece clyde;

    public Game(final @NotNull Maze maze,
                Position pacmanPosition,
                Position blinkyPosition,
                Position pinkyPosition,
                Position inkyPosition,
                Position clydePosition) {
        this.maze = maze;

        pieces = new ArrayList();

        pacman = new Piece(Piece.Type.PACMAN, pacmanPosition);
        pieces.add(pacman);

        blinky = new Piece(Piece.Type.BLINKY, blinkyPosition);
        pieces.add(blinky);

        pinky = new Piece(Piece.Type.PINKY, pinkyPosition);
        pieces.add(pinky);

        inky = new Piece(Piece.Type.INKY, inkyPosition);
        pieces.add(inky);

        clyde = new Piece(Piece.Type.CLYDE, clydePosition);
        pieces.add(clyde);
    }
}
