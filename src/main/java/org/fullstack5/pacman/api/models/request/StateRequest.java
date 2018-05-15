package org.fullstack5.pacman.api.models.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for the request data for getting the game state
 */
@Setter
@Getter
public class StateRequest {
    private String gameId;
}
