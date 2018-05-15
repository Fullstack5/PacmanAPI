package org.fullstack5.pacman.api.models.request;

import lombok.Getter;
import lombok.Setter;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.Direction;

@Getter
@Setter
public class MoveRequest {
    private String gameId;
    private Direction direction;
    private Piece.Type type;
}
