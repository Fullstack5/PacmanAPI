package org.fullstack5.pacman.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.Direction;

/**
 * Class for the request data for moving a piece
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class MoveRequest {
    private String gameId;
    private Direction direction;
    private Piece.Type type;
}
