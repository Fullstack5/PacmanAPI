package org.fullstack5.pacman.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fullstack5.pacman.api.models.PlayerType;

/**
 * Class for the request data for registering a player
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class RegisterPlayerRequest {
    private String gameId;
    private PlayerType type;
}
