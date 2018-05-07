package org.fullstack5.pacmanapi.models.response;

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
