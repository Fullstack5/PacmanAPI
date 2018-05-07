package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.*;
import org.fullstack5.pacmanapi.models.response.GameRegistered;
import org.fullstack5.pacmanapi.models.response.GameState;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    private Map<String, ConnectableFlux<GameState>> fluxes;

    public GameServiceImpl() {
        fluxes = new HashMap();
    }

    @Override
    public Flux<GameState> getState(String gameId) {
        return fluxes.get(gameId);
    }

    @Override
    public GameRegistered register() {
        // generate a non-conflicting gameId for the new game
        String gameId;
        do {
            gameId = PinCode.create();
        } while (fluxes.containsKey(gameId));

        Maze maze = new Maze(20, 20);
        Game game = new Game(maze);
        game.setTime(0);
        game.setPacman(new Piece(new Position(0, 0), Direction.NORTH));

        // runner needs to be final for the Flux's lambda
        final GameRunner runner = new GameRunner(game);

        ConnectableFlux<GameState> flux = Flux
                .interval(Duration.ofSeconds(1))
                .map(t -> runner.step())
                .publish();
        fluxes.put(gameId, flux);
        flux.connect();

        return new GameRegistered(gameId);
    }


}
