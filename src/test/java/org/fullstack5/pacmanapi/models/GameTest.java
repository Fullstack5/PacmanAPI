package org.fullstack5.pacmanapi.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    public Game gameWithEmptyMaze(int height, int width) {
        Maze maze = new Maze(height, width);
        Game game = new Game(maze);
        return game;
    }

    @Test
    public void newPositionNorth() {
        Game game = gameWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = game.newPosition(position, Direction.NORTH);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 5);
        assertEquals("The new position is not what was expected", newPosition.getY(), 4);
    }

    @Test
    public void newPositionSouth() {
        Game game = gameWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = game.newPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 5);
        assertEquals("The new position is not what was expected", newPosition.getY(), 6);
    }

    @Test
    public void newPositionWest() {
        Game game = gameWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = game.newPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 4);
        assertEquals("The new position is not what was expected", newPosition.getY(), 5);
    }

    @Test
    public void newPositionEast() {
        Game game = gameWithEmptyMaze(20, 20);
        Position position = new Position(5, 5);
        Position newPosition = game.newPosition(position, Direction.EAST);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 6);
        assertEquals("The new position is not what was expected", newPosition.getY(), 5);
    }

    @Test
    public void newPositionLoopsY() {
        Game game = gameWithEmptyMaze(20, 20);
        Position position = new Position(5, 19);
        Position newPosition = game.newPosition(position, Direction.SOUTH);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 5);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 19);

        assertEquals("The new position is not what was expected", newPosition.getX(), 5);
        assertEquals("The new position is not what was expected", newPosition.getY(), 0);
    }

    @Test
    public void newPositionLoopsX() {
        Game game = gameWithEmptyMaze(20, 20);
        Position position = new Position(0, 5);
        Position newPosition = game.newPosition(position, Direction.WEST);

        assertEquals("The old position should remain unchanged when getting a new position", position.getX(), 0);
        assertEquals("The old position should remain unchanged when getting a new position", position.getY(), 5);

        assertEquals("The new position is not what was expected", newPosition.getX(), 19);
        assertEquals("The new position is not what was expected", newPosition.getY(), 5);
    }
}