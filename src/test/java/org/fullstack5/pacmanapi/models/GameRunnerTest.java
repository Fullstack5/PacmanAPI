package org.fullstack5.pacmanapi.models;

import org.fullstack5.pacmanapi.GameRunner;
import org.fullstack5.pacmanapi.models.response.GameState;
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


    @Test
    public void newPositionNorth() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.NORTH);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 5, newPosition.getX());
        assertEquals("The new position is not what was expected", 4, newPosition.getY());
    }

    @Test
    public void newPositionSouth() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 5, newPosition.getX());
        assertEquals("The new position is not what was expected", 6, newPosition.getY());
    }

    @Test
    public void newPositionWest() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 4, newPosition.getX());
        assertEquals("The new position is not what was expected", 5, newPosition.getY());
    }

    @Test
    public void newPositionEast() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.EAST);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 6, newPosition.getX());
        assertEquals("The new position is not what was expected", 5, newPosition.getY());
    }

    @Test
    public void newPositionLoopsY() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 19);
        Position newPosition = runner.newPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 19, position.getY());

        assertEquals("The new position is not what was expected", 5, newPosition.getX());
        assertEquals("The new position is not what was expected", 0, newPosition.getY());
    }

    @Test
    public void newPositionLoopsX() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(0, 5);
        Position newPosition = runner.newPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", 0, position.getX());
        assertEquals("The old position should remain unchanged when getting a new position", 5, position.getY());

        assertEquals("The new position is not what was expected", 19, newPosition.getX());
        assertEquals("The new position is not what was expected", 5, newPosition.getY());
    }

    @Test
    public void stepPacmanMoves() {
        Position pacmanSpawn = new Position(10, 10);
        GameRunner runner = runnerWithPacman(20, 20, pacmanSpawn);
        runner.step();

        GameState state = runner.getState();
        assertEquals("Pacman should not have moved", pacmanSpawn, state.getPacman());
        assertEquals("Time should have moved forward", 1, state.getTime());

        runner.setDirection(Direction.NORTH, Piece.Type.PACMAN);
        runner.step();

        state = runner.getState();
        assertNotEquals("Pacman should have moved", pacmanSpawn, state.getPacman());
        assertEquals("Pacman should have moved in the Y direction", 9, state.getPacman().getY());
        assertEquals("Time should have moved forward", 2, state.getTime());
    }
}