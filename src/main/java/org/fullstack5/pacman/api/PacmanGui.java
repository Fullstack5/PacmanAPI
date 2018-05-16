package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.Position;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.MovingPiece;
import reactor.core.publisher.Flux;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * GUI for a pacman game.
 */
public final class PacmanGui {

    private static final int GRID_WIDTH = 40;
    private static final int MS_PER_TICK = 1000;
    private static final int FRAMES_PER_TICK = 10;
    private static final int MS_PER_FRAME = MS_PER_TICK / FRAMES_PER_TICK;

    private final String gameId;
    private final Maze maze;

    private GameState state;

    private int renderProgress = 0;

    public PacmanGui(final String gameId, final Maze maze) {
        this.gameId = gameId;
        this.maze = maze;
    }

    public final void initialize(final Flux<GameState> flux) {
        flux.subscribe(state -> {
            this.state = state;
            renderProgress = 0;
//            System.out.println("Updated state");
        });

        final JFrame frame = new JFrame();
        final JPanel panel = new MyPanel();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);
        frame.pack();
        frame.setSize(maze.getWidth() * GRID_WIDTH + 16, maze.getHeight() * GRID_WIDTH + 38);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Chapter Fullstack 5 Pacman Simulator | PIN: " + gameId);
        frame.setVisible(true);
        frame.addWindowListener(new PacmanWindowListener(frame));

        new Thread(new GuiRunner(frame)).start();
    }

    private class PacmanWindowListener extends WindowAdapter {

        private final JFrame frame;

        public PacmanWindowListener(final JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void windowClosed(final WindowEvent e) {
            frame.dispose();
            // TODO: Stop game on window close.
        }
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
            if (state != null) {
                renderPacman(g);
                renderDots(g, state.getRemainingDots(), 8);
                renderDots(g, state.getRemainingPellets(), 2);
                renderGhost(g, state.getBlinky(), Color.RED);
                renderGhost(g, state.getPinky(), Color.PINK);
                renderGhost(g, state.getInky(), Color.CYAN);
                renderGhost(g, state.getClyde(), Color.ORANGE);
            }
        }

        private void renderDots(final Graphics g, final List<Position> dots, final int size) { // size = 1/X of square
            g.setColor(Color.yellow);
            for (final Position dot : dots) {
                g.fillOval(GRID_WIDTH * dot.getX() + GRID_WIDTH / 2 - GRID_WIDTH / size / 2,
                        GRID_WIDTH * dot.getY() + GRID_WIDTH / 2 - GRID_WIDTH / size / 2,
                        GRID_WIDTH / size, GRID_WIDTH / size);
            }
        }

        private void renderPacman(final Graphics g) {
            final MovingPiece pacman = state.getPacman();
            int animProgress = (renderProgress + 5) % FRAMES_PER_TICK;
            if (animProgress > 6) {
                animProgress = FRAMES_PER_TICK - animProgress;
            }
            g.setColor(Color.yellow);
            final int startAngle = pacman.getDirection().getAngle();
            g.fillArc(
                    calcDrawX(pacman, renderProgress),
                    calcDrawY(pacman, renderProgress),
                    GRID_WIDTH - 1, GRID_WIDTH - 1, startAngle + 45 - animProgress * 9, 270 + animProgress * 18);

//            g.setColor(Color.black);
//            g.drawString(String.format("X = %d; Y = %d; direction = %s; renderProgress = %d", pacman.getPosition().getX(), pacman.getPosition().getY(), pacman.getDirection().name(), renderProgress), 50, 250);
        }

        private void renderGhost(final Graphics g, final MovingPiece ghost, final Color color) {
            g.setColor(ghost.isVulnerable() ? Color.BLUE : color);
            final int drawX = calcDrawX(ghost, renderProgress);
            final int drawY = calcDrawY(ghost, renderProgress);
            g.fillArc(drawX, drawY, GRID_WIDTH - 1, (GRID_WIDTH ) - 1, 0, 180);
            final int[] x = new int[] {drawX, drawX, drawX + GRID_WIDTH / 4, drawX + GRID_WIDTH / 2, drawX + GRID_WIDTH * 3 / 4, drawX + GRID_WIDTH, drawX + GRID_WIDTH};
            final int legsTop = drawY + GRID_WIDTH * 3 / 4;
            final int legsBottom = drawY + GRID_WIDTH - 1;
            final int[] y = new int[] {drawY + GRID_WIDTH / 2, legsBottom, legsTop, legsBottom, legsTop, legsBottom, drawY + GRID_WIDTH / 2};
            g.fillPolygon(x, y, x.length);
            g.setColor(Color.WHITE);
            g.fillOval(drawX + GRID_WIDTH / 8, drawY + GRID_WIDTH / 8, GRID_WIDTH / 4, GRID_WIDTH / 4);
            g.fillOval(drawX + GRID_WIDTH * 5 / 8, drawY + GRID_WIDTH / 8, GRID_WIDTH / 4, GRID_WIDTH / 4);
            if (!ghost.isVulnerable()) {
                g.setColor(Color.BLACK);
                g.drawOval(drawX + GRID_WIDTH / 8, drawY + GRID_WIDTH / 8, GRID_WIDTH / 4, GRID_WIDTH / 4);
                g.fillOval(drawX + GRID_WIDTH / 8 + (ghost.getDirection().getDeltaX() + 1) * GRID_WIDTH / 16, drawY + GRID_WIDTH / 8 + (ghost.getDirection().getDeltaY() + 1) * GRID_WIDTH / 16, GRID_WIDTH / 8, GRID_WIDTH / 8);
                g.drawOval(drawX + GRID_WIDTH * 5 / 8, drawY + GRID_WIDTH / 8, GRID_WIDTH / 4, GRID_WIDTH / 4);
                g.fillOval(drawX + GRID_WIDTH * 5 / 8 + (ghost.getDirection().getDeltaX() + 1) * GRID_WIDTH / 16, drawY + GRID_WIDTH / 8 + (ghost.getDirection().getDeltaY() + 1) * GRID_WIDTH / 16, GRID_WIDTH / 8, GRID_WIDTH / 8);
            }
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

    private static int calcDrawX(final MovingPiece piece, final int renderProgress) {
        if (piece.getOldPosition().equals(piece.getCurrentPosition())) {
            return GRID_WIDTH * piece.getOldPosition().getX();
        }
        return GRID_WIDTH * piece.getOldPosition().getX() + GRID_WIDTH * renderProgress * piece.getDirection().getDeltaX() / FRAMES_PER_TICK;
    }

    private static int calcDrawY(final MovingPiece piece, final int renderProgress) {
        if (piece.getOldPosition().equals(piece.getCurrentPosition())) {
            return GRID_WIDTH * piece.getOldPosition().getY();
        }
        return GRID_WIDTH * piece.getOldPosition().getY() + GRID_WIDTH * renderProgress * piece.getDirection().getDeltaY() / FRAMES_PER_TICK;
    }
}
