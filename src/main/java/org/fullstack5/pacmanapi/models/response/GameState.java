package org.fullstack5.pacmanapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fullstack5.pacmanapi.models.Position;

import java.util.List;

/**
 * Immutable instance of the game state in time.
 */
@Getter
@AllArgsConstructor
public final class GameState {
    private final long time;
    private final List<Position> remainingDots;
    private final List<Position> remainingPellets;
    private final Position pacman;
    private final Position blinky;
    private final Position pinky;
    private final Position inky;
    private final Position clyde;
}
