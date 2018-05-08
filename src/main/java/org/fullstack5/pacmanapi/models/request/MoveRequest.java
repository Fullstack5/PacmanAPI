package org.fullstack5.pacmanapi.models.request;

import lombok.Getter;
import lombok.Setter;
import org.fullstack5.pacmanapi.models.Direction;
import org.fullstack5.pacmanapi.models.Piece;

@Getter
@Setter
public class MoveRequest {
    private String gameId;
    private Direction direction;
    private Piece.Type type;
}
