package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A movable piece within the game.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Piece {
    private Position position;
    private Direction direction;
}
