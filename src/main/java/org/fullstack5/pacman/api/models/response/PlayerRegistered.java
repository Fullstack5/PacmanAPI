package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fullstack5.pacman.api.models.Maze;

/**
 * Immutable response for registering a player
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class PlayerRegistered {
    private String authId;
    private Maze maze;
}
