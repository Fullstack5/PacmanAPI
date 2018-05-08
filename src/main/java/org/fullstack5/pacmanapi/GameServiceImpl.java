package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.*;
import org.fullstack5.pacmanapi.models.response.GameRegistered;
import org.fullstack5.pacmanapi.models.response.GameState;
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
        game.setTime(0);
        game.setPacman(new Piece(new Position(0, 0), Direction.NORTH));

        // runner needs to be final for the Flux's lambda
        final GameRunner runner = new GameRunner(game);
        games.put(gameId, runner);

        return new GameRegistered(gameId);
    }

    @Override
    public void performMove(String gameId, Direction direction) {
        GameRunner runner = games.get(gameId);
        runner.setMove(direction);
    }


}
