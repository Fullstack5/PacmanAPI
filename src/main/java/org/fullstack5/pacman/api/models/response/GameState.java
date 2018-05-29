package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.State;

import java.util.List;

/**
 * Immutable instance of the game state in time.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class GameState {
    private long time;
    private State state;
    private List<Position> remainingDots;
    private List<Position> remainingPellets;
    private MovingPiece pacman;
    private MovingPiece blinky;
    private MovingPiece pinky;
    private MovingPiece inky;
    private MovingPiece clyde;

    @Override
    public final String toString() {
        return String.format("State [time=%d] %s", time, state);
    }
}
