package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Immutable instance of the game state in time.
 */
@Getter
@AllArgsConstructor
public final class GameState {
    private final long time;
    private final Position pacman;
}
