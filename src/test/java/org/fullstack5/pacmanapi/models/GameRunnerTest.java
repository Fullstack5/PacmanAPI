package org.fullstack5.pacmanapi.models;

import org.fullstack5.pacmanapi.GameRunner;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameRunnerTest {

    private GameRunner runnerWithEmptyMaze(int height, int width) {
        boolean[][] noWalls = new boolean[width][height];
        Position spawn = new Position(0, 0);
        List<Position> noDots = Collections.emptyList();
        List<Position> noPowerPellets = Collections.emptyList();
        Maze maze = new Maze(noWalls, noDots, noPowerPellets, spawn, spawn, spawn, spawn, spawn);
        Game game = new Game(maze);
        return new GameRunner(game);
    }

    @Test
    public void newPositionNorth() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.NORTH);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 5);
        assertEquals("The new position is not what was expected", newPosition.getY(), 4);
    }

    @Test
    public void newPositionSouth() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 5);
        assertEquals("The new position is not what was expected", newPosition.getY(), 6);
    }

    @Test
    public void newPositionWest() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 4);
        assertEquals("The new position is not what was expected", newPosition.getY(), 5);
    }

    @Test
    public void newPositionEast() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = runner.newPosition(position, Direction.EAST);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 6);
        assertEquals("The new position is not what was expected", newPosition.getY(), 5);
    }

    @Test
    public void newPositionLoopsY() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(5, 19);
        Position newPosition = runner.newPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 19);

        assertEquals("The new position is not what was expected", newPosition.getX(), 5);
        assertEquals("The new position is not what was expected", newPosition.getY(), 0);
    }

    @Test
    public void newPositionLoopsX() {
        GameRunner runner = runnerWithEmptyMaze(20, 20);
        Position position = new Position(0, 5);
        Position newPosition = runner.newPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 0);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 19);
        assertEquals("The new position is not what was expected", newPosition.getY(), 5);
    }
}