package org.fullstack5.pacmanapi.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fullstack5.pacmanapi.models.Position;

/**
 * Immutable instance of the game state in time.
 */
@Getter
@AllArgsConstructor
public final class GameState {
    private final long time;
    private final Position pacman;
}
