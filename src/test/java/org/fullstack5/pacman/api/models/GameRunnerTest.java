package org.fullstack5.pacman.api.models;

import org.fullstack5.pacman.api.GameRunner;
import org.fullstack5.pacman.api.models.response.GameState;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameRunnerTest {

    private GameRunner runnerWithEmptyMaze(int height, int width) {
        return runnerWithPacmanAndGhost(height, width, null, null);
    }

    private GameRunner runnerWithPacman(int height, int width, Position pacmanSpawn) {
        return runnerWithPacmanAndGhost(height, width, pacmanSpawn, null);
    }

    private GameRunner runnerWithPacmanAndGhost(int height, int width, Position pacmanSpawn, Position blinkySpawn) {
        boolean[][] noWalls = new boolean[width][height];
        List<Position> noDots = Collections.emptyList();
        List<Position> noPowerPellets = Collections.emptyList();
        Maze maze = new Maze(noWalls, noDots, noPowerPellets, pacmanSpawn, blinkySpawn, null, null, null);
        Game game = new Game(maze);
        return new GameRunner(game);
    }

    private GameRunner runnerWithWalls(boolean[][] walls) {
        List<Position> noDots = Collections.emptyList();
        List<Position> noPowerPellets = Collections.emptyList();
        Maze maze = new Maze(walls, noDots, noPowerPellets, null, null, null, null, null);
        Game game = new Game(maze);
        return new GameRunner(game);
    }

    @Test
    public void determineNewPositionNorth() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.determineNewPosition(position, Direction.NORTH);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 5, newPosition.getX());
        assertEquals("The new position is not what was expected", 4, newPosition.getY());
    }

    @Test
    public void determineNewPositionSouth() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.determineNewPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 5, newPosition.getX());
        assertEquals("The new position is not what was expected", 6, newPosition.getY());
    }

    @Test
    public void determineNewPositionWest() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.determineNewPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 4, newPosition.getX());
        assertEquals("The new position is not what was expected", 5, newPosition.getY());
    }

    @Test
    public void determineNewPositionEast() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.determineNewPosition(position, Direction.EAST);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 6, newPosition.getX());
        assertEquals("The new position is not what was expected", 5, newPosition.getY());
    }

    @Test
    public void determineNewPositionLoopsY() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 19);
        Position newPosition = runner.determineNewPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 19, position.getY());

        assertEquals("The new position is not what was expected", 5, newPosition.getX());
        assertEquals("The new position is not what was expected", 0, newPosition.getY());
    }

    @Test
    public void determineNewPositionLoopsX() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(0, 5);
        Position newPosition = runner.determineNewPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", 0, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 19, newPosition.getX());
        assertEquals("The new position is not what was expected", 5, newPosition.getY());
    }

    @Test
    public void determineNewPositionWallCollision() {
        boolean[][] walls = new boolean[][] {
            {true, true, true},
            {true, false, true},
            {true, true, true}
        };
        GameRunner runner = runnerWithWalls(walls);
        Position position = new Position(1, 1);
        Position newPosition = runner.determineNewPosition(position, Direction.WEST);

        assertEquals("The new position should be the same as the old position", position.getX(), newPosition.getX());
        assertEquals("The new position should be the same as the old position", position.getY(), newPosition.getY());
    }

    @Test
    public void performStepPacmanMoves() {
        Position pacmanSpawn = new Position(10, 10);
        GameRunner runner = runnerWithPacman(20, 20, pacmanSpawn);
        runner.setDirection(Direction.NORTH, Piece.Type.PACMAN);
        runner.performStep();

        GameState state = runner.createState();
        assertNotEquals("Pacman should have moved", pacmanSpawn, state.getPacman().getCurrentPosition());
        assertEquals("Pacman should have moved in the Y direction", 9, state.getPacman().getCurrentPosition().getY());
        assertEquals("Time should have moved forward", 1, state.getTime());
    }

    @Test
    public void performStepPacmanMovesDefaultWest() {
        Position pacmanSpawn = new Position(10, 10);
        GameRunner runner = runnerWithPacman(20, 20, pacmanSpawn);
        runner.performStep();

        GameState state = runner.createState();
        assertNotEquals("Pacman should have moved", pacmanSpawn, state.getPacman().getCurrentPosition());
        assertEquals("Pacman should have moved in the X direction", 9, state.getPacman().getCurrentPosition().getX());
        assertEquals("Time should have moved forward", 1, state.getTime());
    }

    @Test
    public void boundedMoveUpperBound() {
        GameRunner runner = runnerWithEmptyMaze(20,20);
        int result = runner.boundedMove(19, 1, 20);

        assertEquals("Should roll over", 0, result);
    }

    @Test
    public void boundedMoveLowerBound() {
        GameRunner runner = runnerWithEmptyMaze(20,20);
        int result = runner.boundedMove(0, -1, 20);

        assertEquals("Should roll over", 19, result);
    }

    @Test
    public void collidedNoCollision() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Piece pacman = new Piece(Piece.Type.PACMAN, new Position(1, 1));
        pacman.setPreviousPosition(new Position(2, 1));
        Piece ghost = new Piece(Piece.Type.BLINKY, new Position(2, 1));
        ghost.setPreviousPosition(new Position(3, 1));

        boolean result = runner.collided(pacman, ghost);

        assertEquals("These pieces should not be collided", false, result);
    }

    @Test
    public void collidedCollision() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Piece pacman = new Piece(Piece.Type.PACMAN, new Position(1, 1));
        Piece ghost = new Piece(Piece.Type.BLINKY, new Position(1, 1));

        boolean result = runner.collided(pacman, ghost);

        assertEquals("These pieces should be collided", true, result);
    }

    @Test
    public void collidedCrossedCollision() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Piece pacman = new Piece(Piece.Type.PACMAN, new Position(1, 0));
        pacman.setPreviousPosition(new Position(2, 0));
        Piece ghost = new Piece(Piece.Type.BLINKY, new Position(2, 0));
        ghost.setPreviousPosition(new Position(1, 0));

        boolean result = runner.collided(pacman, ghost);

        assertEquals("These pieces should be collided", true, result);
    }
}