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

    private Map<String, Game> games;
    private Map<String, ConnectableFlux<GameState>> fluxes;

    public GameServiceImpl() {
        games = new HashMap();
        fluxes = new HashMap();
    }

    @Override
    public Flux<GameState> get(String pinCode) {
        return fluxes.get(pinCode);
    }

    @Override
    public GameRegistered register() {
        // generate a non-conflicting pinCode for the new game
        String pinCode;
        do {
            pinCode = PinCode.create();
        } while (games.containsKey(pinCode));

        // and make this pinCode the final gameId for use in the Flux lambda
        final String gameId = pinCode;

        Maze maze = new Maze(20, 20);
        Game game = new Game(maze);
        game.setTime(0);
        final Maze maze = MazeLoader.loadMaze(1);
        game.setPacman(new Piece(new Position(0, 0), Direction.NORTH));
        games.put(gameId, game);

        ConnectableFlux<GameState> flux = Flux
                .interval(Duration.ofSeconds(1))
                .map(t -> this.step(gameId))
                .publish();
        fluxes.put(gameId, flux);
        flux.connect();

        return new GameRegistered(gameId);
    }

    public GameState step(String gameId) {
        Game game = games.get(gameId);

        // update time
        long time = game.getTime();
        time = time + 1;
        game.setTime(time);

        // update pacman
        Piece pacman = game.getPacman();
        pacman.setPosition(game.newPosition(pacman.getPosition(), pacman.getDirection()));

        return new GameState(
                time,
                pacman.getPosition()
        );
    }

}
