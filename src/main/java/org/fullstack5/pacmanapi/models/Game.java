package org.fullstack5.pacmanapi.models;

import lombok.Getter;
import lombok.Setter;

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

    public Game(final Maze maze) {
        this.maze = maze;

        pieces = new ArrayList();

        pacman = new Piece(Piece.Type.PACMAN, maze.getPacmanSpawn());
        pieces.add(pacman);

        blinky = new Piece(Piece.Type.BLINKY, maze.getBlinkySpawn());
        pieces.add(blinky);

        pinky = new Piece(Piece.Type.PINKY, maze.getPinkySpawn());
        pieces.add(pinky);

        inky = new Piece(Piece.Type.INKY, maze.getInkySpawn());
        pieces.add(inky);

        clyde = new Piece(Piece.Type.CLYDE, maze.getClydeSpawn());
        pieces.add(clyde);
    }
}
