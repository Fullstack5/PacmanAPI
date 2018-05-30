package org.fullstack5.pacman.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.Result;

import java.util.List;
import java.util.Optional;

/**
 * Immutable instance of the game state in time.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class GameState {
    private long time;
    private Optional<Result> result;
    private List<Position> remainingDots;
    private List<Position> remainingPellets;
    private MovingPiece pacman;
    private MovingPiece blinky;
    private MovingPiece pinky;
    private MovingPiece inky;
    private MovingPiece clyde;
    private int pacmanScore;
    private int ghostsScore;

    @Override
    public final String toString() {
        StringBuilder representation = new StringBuilder();
        representation.append(String.format("State [time=%d] pacmanScore: %d", time, pacmanScore));
        if (result.isPresent()) {
            representation.append(String.format(" %s", result.get()));
        }
        representation.append(String.format("\nPacman: %s", pacman));
        representation.append(String.format("\nBlinky: %s", blinky));
        representation.append(String.format("\nPinky: %s", pinky));
        representation.append(String.format("\nInky: %s", inky));
        representation.append(String.format("\nClyde: %s", clyde));

        return representation.toString();
    }
}
