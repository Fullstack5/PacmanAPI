package org.fullstack5.pacman.clients.teampacman.pacman;

import lombok.AllArgsConstructor;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import org.fullstack5.pacman.clients.teampacman.AI;
import org.fullstack5.pacman.clients.teampacman.ClientUtils;
import org.fullstack5.pacman.clients.teampacman.ServerComm;

import java.util.List;

@AllArgsConstructor
public final class RandomPacmanAI implements AI {

    private final String gameId;
    private final String authId;
    private final Maze maze;

    @Override
    public final void runAI(final GameState state) {
        final MovingPiece ghost = state.getPacman();
        final List<Direction> directions = ClientUtils.getAvailableDirections(maze, ghost);
        if (directions.size() > 1) {
            directions.remove(ClientUtils.getReverseDirection(ghost.getDirection()));
        }
        final Direction direction = ClientUtils.randomItem(directions);
        performMove(direction);
    }

    private void performMove(final Direction direction) {
        final MoveRequest request = new MoveRequest(gameId, authId, direction, Piece.Type.PACMAN);
        ServerComm.performMove(request);
    }
}
