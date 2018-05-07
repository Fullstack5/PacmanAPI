package org.fullstack5.pacmanapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Position {
    // this class is meant to be immutable
    private int x;
    private int y;
}
