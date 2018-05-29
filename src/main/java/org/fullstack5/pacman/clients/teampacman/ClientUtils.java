package org.fullstack5.pacman.clients.teampacman;

import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import org.fullstack5.pacman.clients.teampacman.models.WeightedPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * General utils available in the client.
 */
public final class ClientUtils {

    private ClientUtils() {}

    public static <T> T randomItem(final List<T> items) {
        return items.get((int) (Math.random() * items.size()));
    }

    public static Direction getReverseDirection(final Direction direction) {
        switch(direction) {
        case NORTH:
            return Direction.SOUTH;
        case EAST:
            return Direction.WEST;
        case SOUTH:
            return Direction.NORTH;
        case WEST:
            return Direction.EAST;
        default:
            return null;
        }
    }

    /**
     * Get all free locations adjacent to the current location.
     */
    public static List<Direction> getAvailableDirections(final Maze maze, final MovingPiece piece) {
        final List<Direction> result = new ArrayList<>();
        final int x = piece.getCurrentPosition().getX();
        final int y = piece.getCurrentPosition().getY();
        if (y == 0 || !maze.isWall(x, y - 1)) {
            result.add(Direction.NORTH);
        }
        if (x == maze.getWidth() - 1 || !maze.isWall(x + 1, y)) {
            result.add(Direction.EAST);
        }
        if (y == maze.getHeight() - 1 || !maze.isWall(x, y + 1)) {
            result.add(Direction.SOUTH);
        }
        if (x == 0 || !maze.isWall(x - 1, y)) {
            result.add(Direction.WEST);
        }
        return result;
    }

    public static Piece.Type getGhostType(final int ghostNumber) {
        switch(ghostNumber) {
        case 0:
            return Piece.Type.BLINKY;
        case 1:
            return Piece.Type.PINKY;
        case 2:
            return Piece.Type.INKY;
        case 3:
            return Piece.Type.CLYDE;
        default:
            throw new IllegalArgumentException("No ghost with number " + ghostNumber);
        }
    }

    public static MovingPiece getGhost(final GameState state, final int ghostNumber) {
        switch(ghostNumber) {
        case 0:
            return state.getBlinky();
        case 1:
            return state.getPinky();
        case 2:
            return state.getInky();
        case 3:
            return state.getClyde();
        default:
            throw new IllegalArgumentException("No ghost with number " + ghostNumber);
        }
    }

    public static Position getPosition(final Maze maze, final Position current, final Direction direction) {
        final int x = (current.getX() + direction.getDeltaX() + maze.getWidth()) % maze.getWidth();
        final int y = (current.getY() + direction.getDeltaY() + maze.getHeight()) % maze.getHeight();
        return new Position(x, y);
    }

    public static <T> List<T> randomize(final T[] values) {
        return randomize(new ArrayList<T>(Arrays.asList(values)));
    }

    public static <T> List<T> randomize(final List<T> values) {
        final List<T> result = new ArrayList<>();
        while (!values.isEmpty()) {
            final T item = randomItem(values);
            result.add(item);
            values.remove(item);
        }
        return result;
    }

    public static <T> void dumpSet(final TreeSet<T> set) {
        System.out.println("Dump of set (" + set.size() + ")");
        for (final T object : set) {
            System.out.println(" " + object);
        }
    }
}
