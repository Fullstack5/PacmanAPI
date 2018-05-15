package org.fullstack5.pacman.api.models.request;

import lombok.Getter;
import lombok.Setter;
import org.fullstack5.pacman.api.models.PlayerType;

/**
 * Class for the request data for registering a player
 */
@Getter
@Setter
public class RegisterPlayerRequest {
    private String gameId;
    private PlayerType type;
}
