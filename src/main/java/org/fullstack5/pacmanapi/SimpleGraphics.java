package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.fullstack5.pacmanapi.models.Piece;
import org.fullstack5.pacmanapi.models.Position;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qc53bf on 8-5-2018.
 */
public class SimpleGraphics {

    public static void main(String... args) {
        final JFrame frame = new JFrame();
        JPanel panel = new MyPanel();
        setKeyboard(panel);
        frame.add(panel);
        frame.pack();
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(new Runner(frame)).start();
    }

    private static final int gridWidth = 40;
    private static final int framesPerMove = 10;
    private static final int msPerFrame = 100;

    private static int progress = 0;
    private static final Piece pacman = new Piece(new Position(1, 1), Direction.EAST);

    private static class Runner implements Runnable {

        private JFrame frame;

        private Runner(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            while (true) {
                progress++;
                if (progress >= 10) {
                    progress = 0;
                    pacman.setPosition(new Position(pacman.getPosition().getX() + pacman.getDirection().getXInc(), pacman.getPosition().getY() + pacman.getDirection().getYInc()));
                    pacman.setDirection(Direction.random());
                }
                frame.repaint();
                try {
                    Thread.sleep(msPerFrame);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class MyPanel extends JPanel {


        @Override
        protected void paintComponent(Graphics g) {
            boolean[][] grid = new boolean[][]{
                    {true, true, true, true, true},
                    {true, false, false, false, true},
                    {true, true, false, true, true},
                    {true, false, false, false, true},
                    {true, true, true, true, true}
            };

            int width = grid.length;
            int height = grid[0].length;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (grid[y][x]) {
                        g.setColor(Color.blue);
                    } else {
                        g.setColor(Color.black);
                    }
                    g.fillRect(x * gridWidth, y * gridWidth, gridWidth - 1, gridWidth - 1);
                }
            }
            int animProgress = (progress + 5) % framesPerMove;
            if (animProgress > 6) {
                animProgress = framesPerMove - animProgress;
            }
            g.setColor(Color.yellow);
            final int startAngle = pacman.getDirection().getAngle();
            g.fillArc(
                    gridWidth * pacman.getPosition().getX() + gridWidth * progress * pacman.getDirection().getXInc() / framesPerMove,
                    gridWidth * pacman.getPosition().getY() + gridWidth * progress * pacman.getDirection().getYInc() / framesPerMove,
                    gridWidth - 1, gridWidth - 1, startAngle + 45 - animProgress * 9, 270 + animProgress * 18);

            g.setColor(Color.black);
            g.drawString(String.format("X = %d; Y = %d; direction = %s; progress = %d", pacman.getPosition().getX(), pacman.getPosition().getY(), pacman.getDirection().name(), progress), 50, 250);
        }
    }

    private static void setKeyboard(JPanel panel) {
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: {
                        pacman.setDirection(Direction.WEST);
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        pacman.setDirection(Direction.EAST);
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        pacman.setDirection(Direction.NORTH);
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        pacman.setDirection(Direction.SOUTH);
                        break;
                    }
                }
            }
        });
    }
}
