package org.fullstack5.pacman.clients.teampacman;

import org.fullstack5.pacman.api.models.PlayerType;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import org.fullstack5.pacman.clients.teampacman.ghosts.RandomGhostAI;
import org.fullstack5.pacman.clients.teampacman.pacman.RandomPacmanAI;
import reactor.core.publisher.Flux;

public final class TeamPacmanClient implements Runnable {

    @Override
    public final void run() {
        startGameAsGhosts();
    }

    private void startGameAsGhosts() {
        final String gameId = ServerComm.startGame();
        final Flux<GameState> flux = ServerComm.establishGameStateFlux(gameId);

        final PlayerRegistered pacmanPlayer = ServerComm.registerPlayer(gameId, PlayerType.PACMAN);
        final RunnerThread pacmanThread = new RunnerThread(new RandomPacmanAI(gameId, pacmanPlayer.getAuthId(), pacmanPlayer.getMaze()));
        flux.subscribe(pacmanThread::updateState);

        final PlayerRegistered ghostPlayer = ServerComm.registerPlayer(gameId, PlayerType.GHOSTS);
        final RunnerThread ghostThread = new RunnerThread(new RandomGhostAI(gameId, ghostPlayer.getAuthId(), ghostPlayer.getMaze()));
        flux.subscribe(ghostThread::updateState);
    }

    public static void main(final String...args) {
        new TeamPacmanClient().run();
    }

}
