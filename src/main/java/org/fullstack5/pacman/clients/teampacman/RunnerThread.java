package org.fullstack5.pacman.clients.teampacman;

import org.fullstack5.pacman.api.models.response.GameState;

import static org.fullstack5.pacman.api.models.State.IN_PROGRESS;

/**
 * Thread responsible for checking for a new state continuously, then executing the AI with that new state.
 *
 * Needed because the Flux Thread cannot block on http requests.
 */
public final class RunnerThread extends Thread {

    private final AI ai;

    private GameState gameState;

    public RunnerThread(final AI ai) {
        this.ai = ai;
        start();
    }

    public final void updateState(final GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public final void run() {
        boolean running = true;
        while (running) {
            long start = System.currentTimeMillis();
            if (gameState != null) {
                if (gameState.getState() != IN_PROGRESS) {
                    running = false;
                }
                try {
                    ai.runAI(gameState);
                } catch(RuntimeException e) {
                    e.printStackTrace();
                    System.exit(-1);
                    throw e;
                }
                gameState = null;
                System.out.println("Calculation took " + (System.currentTimeMillis() - start) + "ms");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
