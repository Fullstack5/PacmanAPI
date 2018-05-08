package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.Maze;
import org.fullstack5.pacmanapi.models.Position;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility Class to load mazes from bitmap files.
 */
public final class MazeLoader {

    private static final int COLOR_EMPTY = -16777216; // new Color(0, 0, 0).getRGB();
    private static final int COLOR_WALL = -16776961; // new Color(0, 0, 255).getRGB();
    private static final int COLOR_PELLET = -3947581; // new Color(195, 195, 195).getRGB();
    private static final int COLOR_PACDOT = -8421505; // new Color(127, 127, 127).getRGB();
    private static final int COLOR_PACMAN_SPAWN = -256; // new Color(255, 255, 0).getRGB();
    private static final int COLOR_BLINKY_SPAWN = -65536; // new Color(255, 0, 0).getRGB();
    private static final int COLOR_PINKY_SPAWN = -20791; // new Color(255, 174, 201).getRGB();
    private static final int COLOR_INKY_SPAWN = -16711681; // new Color(0, 255, 255).getRGB();
    private static final int COLOR_CLYDE_SPAWN = -14066; // new Color(255, 201, 14).getRGB();

    static Maze loadMaze(final int mazeId) throws IOException {
        final String filename = String.format("mazes/maze%s.png", mazeId);
        final Path path = Paths.get(filename);
        final BufferedImage image = ImageIO.read(Files.newInputStream(path));
        return loadMaze(image);
    }

    private static Maze loadMaze(final BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean[][] walls = new boolean[width][height];
        final List<Position> dots = new ArrayList<Position>();
        final List<Position> powerPellets = new ArrayList<Position>();
        Position pacmanSpawn = null;
        Position blinkySpawn = null;
        Position pinkySpawn = null;
        Position inkySpawn = null;
        Position clydeSpawn = null;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean wall = false;
                switch(image.getRGB(x, y)) {
                case COLOR_WALL:
                    wall = true;
                    break;
                case COLOR_EMPTY:
                    break;
                case COLOR_PACDOT:
                    dots.add(new Position(x, y));
                    break;
                case COLOR_PELLET:
                    powerPellets.add(new Position(x, y));
                    break;
                case COLOR_PACMAN_SPAWN:
                    if (pacmanSpawn != null ){
                        throw new IllegalStateException("Multiple pacman spawns found!");
                    }
                    pacmanSpawn = new Position(x, y);
                    break;
                case COLOR_BLINKY_SPAWN:
                    if (blinkySpawn != null ){
                        throw new IllegalStateException("Multiple blinky spawns found!");
                    }
                    blinkySpawn = new Position(x, y);
                    break;
                case COLOR_PINKY_SPAWN:
                    if (pinkySpawn != null ){
                        throw new IllegalStateException("Multiple pinky spawns found!");
                    }
                    pinkySpawn = new Position(x, y);
                    break;
                case COLOR_INKY_SPAWN:
                    if (inkySpawn != null ){
                        throw new IllegalStateException("Multiple inky spawns found!");
                    }
                    inkySpawn = new Position(x, y);
                    break;
                case COLOR_CLYDE_SPAWN:
                    if (clydeSpawn != null ){
                        throw new IllegalStateException("Multiple clyde spawns found!");
                    }
                    clydeSpawn = new Position(x, y);
                    break;
                };
                walls[x][y] = wall;
            }
        }
        final Maze maze = new Maze(walls, dots, powerPellets, pacmanSpawn, blinkySpawn, pinkySpawn, inkySpawn, clydeSpawn);
        validateMaze(maze);
        return maze;
    }

    private static void validateMaze(final Maze maze) {
        validateWallConsistency(maze.getWalls());
        if (maze.getPacmanSpawn() == null) {
            throw new IllegalStateException("Pacman spawn position not present!");
        }
        if (maze.getBlinkySpawn() == null) {
            throw new IllegalStateException("Blinky spawn position not present!");
        }
        if (maze.getPinkySpawn() == null) {
            throw new IllegalStateException("Pinky spawn position not present!");
        }
        if (maze.getInkySpawn() == null) {
            throw new IllegalStateException("Inky spawn position not present!");
        }
        if (maze.getClydeSpawn() == null) {
            throw new IllegalStateException("Clyde spawn position not present!");
        }
    }

    private static void validateWallConsistency(final boolean[][] walls) {
        final int width = walls.length;
        final int height = walls[0].length;
        for (int x = 0; x < width; x++) {
            if (walls[x][0] ^ !walls[x][height - 1]) {
                throw new IllegalStateException("Illegal wrap-around at x = " + x);
            }
        }
        for (int y = 0; y < height; y++) {
            if (walls[0][y] ^ !walls[width - 1][y]) {
                throw new IllegalStateException("Illegal wrap-around at y = " + y);
            }
        }
    }
}
