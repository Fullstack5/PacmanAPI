package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Game;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.Maze;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    private Map<String, GameRunner> games;

    public GameServiceImpl() {
        games = new HashMap();
    }

    @Override
    public Flux<GameState> getState(String gameId) {
        return games.get(gameId).getFlux();
    }

    @Override
    public GameRegistered register() {
        // generate a non-conflicting gameId for the new game
        String gameId;
        do {
            gameId = PinCode.create();
        } while (games.containsKey(gameId));

        final Maze maze;
        try {
            maze = MazeLoader.loadMaze(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Game game = new Game(maze);

        final GameRunner runner = new GameRunner(game);
        games.put(gameId, runner);
        runner.start();

        return new GameRegistered(gameId);
    }

    @Override
    public void performMove(String gameId, Direction direction, Piece.Type type) {
        GameRunner runner = games.get(gameId);
        runner.setDirection(direction, type);
    }


}
