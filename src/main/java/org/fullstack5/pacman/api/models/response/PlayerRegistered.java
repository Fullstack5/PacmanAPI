package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Immutable response for registering a player
 */
@Getter
@AllArgsConstructor
public class PlayerRegistered {
    private final String authId;
}
