package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Position;

/**
 * Immutable class representing a move performed by a piece
 */
@Getter
@AllArgsConstructor
public class MovingPiece {
    final Position oldPosition;
    final Position currentPosition;
    final Direction direction;
}
