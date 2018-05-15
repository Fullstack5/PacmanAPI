package org.fullstack5.pacman.api.models.request;

import lombok.Getter;
import lombok.Setter;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.Direction;

/**
 * Class for the request data for moving a piece
 */
@Getter
@Setter
public class MoveRequest {
    private String gameId;
    private String authId;
    private Direction direction;
    private Piece.Type type;
}
