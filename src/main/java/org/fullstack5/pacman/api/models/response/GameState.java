package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.State;

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
    private final MovingPiece pacman;
    private final MovingPiece blinky;
    private final MovingPiece pinky;
    private final MovingPiece inky;
    private final MovingPiece clyde;
}
