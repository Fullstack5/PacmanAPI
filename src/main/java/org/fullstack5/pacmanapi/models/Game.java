package org.fullstack5.pacmanapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private long time;
    private Piece pacman;
}
