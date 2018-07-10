package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.*;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    private Map<String, GameRunner> games;

    public GameServiceImpl() {
        games = new HashMap();
    }

    @Override
    public final Flux<GameState> getState(final String gameId) {
        final GameRunner game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("No game with gameId " + gameId);
        }
        return game.getFlux();
    }

    @Override
    public GameRegistered registerGame(
            Duration step,
            PacmanRunner pacmanRunner,
            GhostRunner ghostRunner) {
        // generate a non-conflicting gameId for the new game
        String newGameId;
        do {
            newGameId = PinCode.create();
        } while (games.containsKey(newGameId));
        // make it final for usage in lambda expressions
        final String gameId = newGameId;

        final Maze maze;
        try {
            maze = MazeLoader.loadMaze(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Game game = new Game(maze);

        final GameRunner runner = new GameRunner(game, step);
        games.put(gameId, runner);
        Runnable removeGame = () -> games.remove(gameId);
        runner.start(gameId, step);
        runner.getFlux().subscribe(null, null, removeGame);

        return new GameRegistered(gameId);
    }

    @Override
    public PlayerRegistered registerPlayer(String gameId, PlayerType type) {
        // generate new random authId
        String authId = AuthenticationToken.create();

        GameRunner runner = games.get(gameId);
        runner.setPlayerAuthId(authId, type);

        return new PlayerRegistered(authId, runner.getMaze());
    }

    @Override
    public void performMove(String gameId, String authId, Direction direction, Piece.Type type) {
        final GameRunner runner = games.get(gameId);
        if (runner == null ) {
            System.err.println("Received order for gameId = " + gameId + ", but game has already ended!");
            return;
        }
        runner.setDirection(authId, direction, type);
    }
}
