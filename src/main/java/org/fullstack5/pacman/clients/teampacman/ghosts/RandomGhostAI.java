package org.fullstack5.pacman.clients.teampacman.ghosts;

import lombok.AllArgsConstructor;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import org.fullstack5.pacman.clients.teampacman.AI;
import org.fullstack5.pacman.clients.teampacman.ClientUtils;
import org.fullstack5.pacman.clients.teampacman.ServerComm;

import java.util.List;

@AllArgsConstructor
public final class RandomGhostAI implements AI {

    private static final int GHOST_COUNT = 4;

    private final String gameId;
    private final String authId;
    private final Maze maze;

    @Override
    public final void runAI(final GameState state) {
        for (int i = 0; i < GHOST_COUNT; i++) {
            final MovingPiece ghost = ClientUtils.getGhost(state, i);
            final List<Direction> directions = ClientUtils.getAvailableDirections(maze, ghost);
            if (directions.size() > 1) {
                directions.remove(ClientUtils.getReverseDirection(ghost.getDirection()));
            }
            final Direction direction = ClientUtils.randomItem(directions);
            performMove(i, direction);
        }
    }

    private void performMove(final int ghostNumber, final Direction direction) {
        System.out.println("Performing move " + ghostNumber + " " + direction);
        final MoveRequest request = new MoveRequest(gameId, authId, direction, ClientUtils.getGhostType(ghostNumber));
        ServerComm.performMove(request);
    }
}
