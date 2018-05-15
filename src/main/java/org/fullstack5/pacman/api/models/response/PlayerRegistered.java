package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fullstack5.pacman.api.models.Maze;

/**
 * Immutable response for registering a player
 */
@Getter
@AllArgsConstructor
public class PlayerRegistered {
    private final String authId;
    private final Maze maze;
}
