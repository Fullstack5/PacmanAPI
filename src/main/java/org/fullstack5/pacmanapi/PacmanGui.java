package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.Direction;
import org.fullstack5.pacmanapi.models.Maze;
import org.fullstack5.pacmanapi.models.Piece;
import org.fullstack5.pacmanapi.models.Position;
import org.fullstack5.pacmanapi.models.response.GameState;
import reactor.core.publisher.Flux;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI for a pacman game.
 */
public final class PacmanGui {

    private static final int GRID_WIDTH = 40;
    private static final int MS_PER_TICK = 1000;
    private static final int FRAMES_PER_TICK = 20;
    private static final int MS_PER_FRAME = MS_PER_TICK / FRAMES_PER_TICK;

    private final Maze maze;

    private GameState state;

    private int renderProgress = 0;

    public static void main(final String... args) {
//        boolean[][] grid = new boolean[][]{
//                {true, true, true, true, true},
//                {true, false, false, false, true},
//                {true, true, false, true, true},
//                {true, false, false, false, true},
//                {true, true, true, true, true}
//        };
//        new PacmanGui().initialize();
    }

    public PacmanGui(final Maze maze) {
        this.maze = maze;
    }

    public final void initialize(final Flux<GameState> flux) {
        flux.all(state -> {this.state = state;return true;});

        final JFrame frame = new JFrame();
        final JPanel panel = new MyPanel();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new PacmanKeyListener());
        frame.add(panel);
        frame.pack();
        frame.setSize(maze.getWidth() * GRID_WIDTH + 16, maze.getHeight() * GRID_WIDTH + 38);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Chapter Fullstack 5 Pacman Simulator");
        frame.setVisible(true);

        new Thread(new GuiRunner(frame)).start();
    }

    private class GuiRunner implements Runnable {

        private JFrame frame;

        private GuiRunner(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            while (true) {
                renderProgress++;
                if (renderProgress >= 10) {
                    renderProgress = 0;
                    final Piece pacman = state.getPacman();
                    pacman.setPosition(new Position(pacman.getPosition().getX() + pacman.getDirection().getXInc(), pacman.getPosition().getY() + pacman.getDirection().getYInc()));
                    pacman.setDirection(Direction.random());
                }
                frame.repaint();
                try {
                    Thread.sleep(MS_PER_FRAME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyPanel extends JPanel {

        @Override
        protected void paintComponent(final Graphics g) {
            renderMaze(g);
            renderPacman(g);
        }

        private void renderPacman(final Graphics g) {
            if (state == null) {
                return;
            }
            final Piece pacman = state.getPacman();
            int animProgress = (renderProgress + 5) % FRAMES_PER_TICK;
            if (animProgress > 6) {
                animProgress = FRAMES_PER_TICK - animProgress;
            }
            g.setColor(Color.yellow);
            final int startAngle = pacman.getDirection().getAngle();
            g.fillArc(
                    GRID_WIDTH * pacman.getPosition().getX() + GRID_WIDTH * renderProgress * pacman.getDirection().getXInc() / FRAMES_PER_TICK,
                    GRID_WIDTH * pacman.getPosition().getY() + GRID_WIDTH * renderProgress * pacman.getDirection().getYInc() / FRAMES_PER_TICK,
                    GRID_WIDTH - 1, GRID_WIDTH - 1, startAngle + 45 - animProgress * 9, 270 + animProgress * 18);

            g.setColor(Color.black);
            g.drawString(String.format("X = %d; Y = %d; direction = %s; renderProgress = %d", pacman.getPosition().getX(), pacman.getPosition().getY(), pacman.getDirection().name(), renderProgress), 50, 250);
        }

        private void renderMaze(final Graphics g) {
            final int width = maze.getWidth();
            final int height = maze.getHeight();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (maze.isWall(x, y)) {
                        g.setColor(Color.blue);
                    } else {
                        g.setColor(Color.black);
                    }
                    g.fillRect(x * GRID_WIDTH, y * GRID_WIDTH, GRID_WIDTH - 1, GRID_WIDTH - 1);
                }
            }
        }
    }

    private class PacmanKeyListener extends KeyAdapter {

        @Override
        public final void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: {
                    state.getPacman().setDirection(Direction.WEST);
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    state.getPacman().setDirection(Direction.EAST);
                    break;
                }
                case KeyEvent.VK_UP: {
                    state.getPacman().setDirection(Direction.NORTH);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    state.getPacman().setDirection(Direction.SOUTH);
                    break;
                }
            }
        }
    }
}
