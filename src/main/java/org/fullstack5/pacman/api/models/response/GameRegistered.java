package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Immutable game registered response
 */
@Getter
@AllArgsConstructor
public class GameRegistered {
    private final String gameId;
}
