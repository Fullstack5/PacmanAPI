package org.fullstack5.pacman.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for the request data for getting the game state
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class StateRequest {
    private String gameId;
}
