package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class is meant to be immutable.
 */
@Getter
@AllArgsConstructor
public final class Position {
    private final int x;
    private final int y;
}
