package org.fullstack5.pacman.clients.teampacman;

import org.fullstack5.pacman.api.models.GhostRunner;
import org.fullstack5.pacman.api.models.PacmanRunner;
import org.fullstack5.pacman.api.models.PlayerType;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import org.fullstack5.pacman.clients.teampacman.ghosts.AStarGhostAI;
import org.fullstack5.pacman.clients.teampacman.ghosts.RandomGhostAI;
import org.fullstack5.pacman.clients.teampacman.pacman.RandomPacmanAI;
import reactor.core.publisher.Flux;

public final class TeamPacmanClient implements Runnable {
    private String gameId;
    private PacmanRunner pacmanRunner;
    private GhostRunner ghostRunner;

    public TeamPacmanClient(String gameId, PacmanRunner pacmanRunner) {
        this.gameId = gameId;
        this.pacmanRunner = pacmanRunner;
    }

    public TeamPacmanClient(String gameId, GhostRunner ghostRunner) {
        this.gameId = gameId;
        this.ghostRunner = ghostRunner;
    }

    public TeamPacmanClient(String gameId, PacmanRunner pacmanRunner, GhostRunner ghostRunner) {
        this.gameId = gameId;
        this.pacmanRunner = pacmanRunner;
        this.ghostRunner = ghostRunner;
    }

    @Override
    public final void run() {
        if (gameId == null) {
            gameId = ServerComm.startGame();
        }
        final Flux<GameState> flux = ServerComm.establishGameStateFlux(gameId);

        PlayerRegistered player;
        RunnerThread thread;
        if (pacmanRunner != null) {
            switch (pacmanRunner) {
                case RANDOM:
                    player = ServerComm.registerPlayer(gameId, PlayerType.PACMAN);
                    thread = new RunnerThread(new RandomPacmanAI(gameId, player.getAuthId(), player.getMaze()));
                    flux.subscribe(thread::updateState);
                    break;
                default:
                    // do nothing
            }
        }

        if (ghostRunner != null) {
            switch (ghostRunner) {
                case RANDOM:
                    player = ServerComm.registerPlayer(gameId, PlayerType.GHOSTS);
                    thread = new RunnerThread(new RandomGhostAI(gameId, player.getAuthId(), player.getMaze()));
                    flux.subscribe(thread::updateState);
                    break;
                case ASTAR:
                    player = ServerComm.registerPlayer(gameId, PlayerType.GHOSTS);
                    thread = new RunnerThread(new AStarGhostAI(gameId, player.getAuthId(), player.getMaze()));
                    flux.subscribe(thread::updateState);
                    break;
                default:
                    // do nothing
            }
        }
    }

    public static void main(final String...args) {
        new TeamPacmanClient(null, PacmanRunner.RANDOM, GhostRunner.RANDOM).run();
    }

}
