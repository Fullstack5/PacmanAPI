package org.fullstack5.pacman.clients.teampacman.ghosts;

import lombok.AllArgsConstructor;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import org.fullstack5.pacman.clients.teampacman.AI;
import org.fullstack5.pacman.clients.teampacman.ClientUtils;
import org.fullstack5.pacman.clients.teampacman.ServerComm;
import org.fullstack5.pacman.clients.teampacman.models.WeightedPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@AllArgsConstructor
public final class SurroundingAStarGhostAI implements AI {

    private static final int GHOST_COUNT = 4;

    private final String gameId;
    private final String authId;
    private final Maze maze;

    @Override
    public final void runAI(final GameState state) {
        System.out.println("\nTurn " + state.getTime());
        final Direction[] directions = Direction.values();
        for (int i = 0; i < GHOST_COUNT; i++) {
            final MovingPiece ghost = ClientUtils.getGhost(state, i);
            final MovingPiece pacman = state.getPacman();
            final List<WeightedPosition> path = calculateAStarPath(maze, ghost, pacman, directions[i]);
            System.out.println("Found path for " + ClientUtils.getGhostType(i) + ":\n" + path);
            System.out.println("Giving order " + path.get(0).getDirectionToPosition());
            performMove(i, path.get(0).getDirectionToPosition());
        }
    }

    static List<WeightedPosition> calculateAStarPath(final Maze maze, final MovingPiece origin, final MovingPiece target, final Direction surroundDirection) {
        final Position newPosition = calculateNewPosition(maze, target.getCurrentPosition(), surroundDirection);
        final MovingPiece newTarget = new MovingPiece(target.getCurrentPosition(), newPosition, surroundDirection, target.isVulnerable(), target.isActive());
        List<WeightedPosition> result = AStarGhostAI.calculateAStarPath(maze, origin, target);
        final WeightedPosition last = result.get(result.size() - 1);
        result.add(new WeightedPosition(target.getCurrentPosition(), last, Direction.reverse(surroundDirection), last.getDistanceFromSource() + 1, 0));
        return result;
    }

    private static Position calculateNewPosition(final Maze maze, final Position origin, final Direction direction) {
        final Position newPosition = ClientUtils.getPosition(maze, origin, direction);
        if (maze.isWall(newPosition)) {
            return origin;
        }
        return newPosition;
    }

    private void performMove(final int ghostNumber, final Direction direction) {
        final MoveRequest request = new MoveRequest(gameId, authId, direction, ClientUtils.getGhostType(ghostNumber));
        ServerComm.performMove(request);
    }
}
