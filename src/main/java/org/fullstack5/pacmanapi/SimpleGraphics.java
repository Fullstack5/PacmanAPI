package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    private static int positionX = 10;
    private static int positionY = 10;
    private static Direction direction = Direction.EAST;

    private static class Runner implements Runnable {

        private JFrame frame;

        private Runner(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {

            int counter = 0;
            while (true) {

                if (counter >= 30 && counter < 60) {
                    direction = Direction.SOUTH;
                    positionY++;
                }

                if (counter >=60 && counter < 90) {
                    direction = Direction.WEST;
                    positionX--;

                }  if (counter >=90 && counter < 120) {
                    direction = Direction.NORTH;
                    positionY--;

                }else {
                    positionX++;
                }

                counter++;
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

        int x = gridWidth;
        int y = gridWidth;

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
            int animProgress = positionX % framesPerMove;
            if (animProgress > 6) {
                animProgress = framesPerMove - animProgress;
            }
            g.setColor(Color.yellow);
            final int startAngle;


            switch (direction) {
                case NORTH:
                    startAngle = 90;
                    y = gridWidth / positionY / framesPerMove;
                    break;
                case EAST:
                    startAngle = 0;
                    x = gridWidth * positionX / framesPerMove;

                    break;
                case SOUTH:
                    startAngle = 270;
                    y = gridWidth * positionY / framesPerMove;
                    break;
                case WEST:
                    startAngle = 180;
                    x = gridWidth / positionX / framesPerMove;
                    break;
                default:
                    throw new IllegalArgumentException("Direction " + direction + " not known");
            }

            g.fillArc(x, y, gridWidth - 1, gridWidth - 1, startAngle + 45 - animProgress * 9, 270 + animProgress * 18);
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
                        direction = Direction.WEST;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        direction = Direction.EAST;
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        direction = Direction.NORTH;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        direction = Direction.SOUTH;
                        break;
                    }
                }
            }
        });
    }
}