package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Immutable game registered response
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class GameRegistered {
    private String gameId;
}
