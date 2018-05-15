package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Position;

/**
 * Immutable class representing a move performed by a piece
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class MovingPiece {
    private Position oldPosition;
    private Position currentPosition;
    private Direction direction;
    private boolean vulnerable;
}
