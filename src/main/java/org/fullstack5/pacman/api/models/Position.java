package org.fullstack5.pacman.api.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class is meant to be immutable.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public final class Position {
    private int x;
    private int y;
}
