package org.fullstack5.pacman.api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Representation of a game within the server.
 */
@Getter
@Setter
public final class Game {

    private final Maze maze;
    private long time;
    private int ticksVulnerable;
    private Optional<Result> result;
    private final List<Position> remainingPacdots = new ArrayList<>();
    private final List<Position> remainingPellets = new ArrayList<>();
    private final List<Piece> pieces;
    private final List<Piece> ghosts;
    private final Piece pacman;
    private final Piece blinky;
    private final Piece pinky;
    private final Piece inky;
    private final Piece clyde;

    public Game(final Maze maze) {
        this.maze = maze;

        this.result = Optional.empty();

        remainingPacdots.addAll(maze.getDots());
        remainingPellets.addAll(maze.getPowerPellets());

        final List<Piece> pieces = new ArrayList<>();
        final List<Piece> ghosts = new ArrayList<>();

        pacman = new Piece(Piece.Type.PACMAN, maze.getPacmanSpawn());
        pieces.add(pacman);

        blinky = new Piece(Piece.Type.BLINKY, maze.getBlinkySpawn());
        pieces.add(blinky);
        ghosts.add(blinky);

        pinky = new Piece(Piece.Type.PINKY, maze.getPinkySpawn(), 5);
        pieces.add(pinky);
        ghosts.add(pinky);

        inky = new Piece(Piece.Type.INKY, maze.getInkySpawn(), 25);
        pieces.add(inky);
        ghosts.add(inky);

        clyde = new Piece(Piece.Type.CLYDE, maze.getClydeSpawn(), 45);
        pieces.add(clyde);
        ghosts.add(clyde);

        this.pieces = Collections.unmodifiableList(pieces);
        this.ghosts = Collections.unmodifiableList(ghosts);
    }

    public void reduceTicksVulnerable() {
        if (ticksVulnerable > 0) {
            ticksVulnerable -= 1;
        }
    }
}
