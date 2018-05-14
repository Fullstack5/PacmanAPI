package org.fullstack5.pacmanapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fullstack5.pacmanapi.models.Piece;
import org.fullstack5.pacmanapi.models.Position;
import org.fullstack5.pacmanapi.models.State;

import java.util.List;

/**
 * Immutable instance of the game state in time.
 */
@Getter
@AllArgsConstructor
public final class GameState {
    private final long time;
    private final State state;
    private final List<Position> remainingDots;
    private final List<Position> remainingPellets;
    private final Piece pacman;
    private final Piece blinky;
    private final Piece pinky;
    private final Piece inky;
    private final Piece clyde;
}
