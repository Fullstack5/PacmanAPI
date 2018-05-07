package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.*;
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
    public String register() {
        // generate a new pinCode for the new game
        String newPinCode;
        do {
            newPinCode = PinCode.create();
        } while (games.containsKey(newPinCode));

        // and make this pinCode final to use in the Flux lambda
        final String pinCode = newPinCode;

        Game game = new Game();
        game.setTime(0);
        Maze maze = new Maze();
        maze.setHeight(20);
        maze.setWidth(20);
        game.setMaze(maze);
        game.setPacman(new Piece(new Position(0, 0), Direction.NORTH));
        games.put(pinCode, game);

        ConnectableFlux<GameState> flux = Flux
                .interval(Duration.ofSeconds(1))
                .map(t -> this.step(pinCode))
                .publish();
        fluxes.put(pinCode, flux);
        flux.connect();

        return pinCode;
    }

    public GameState step(String pinCode) {
        Game game = games.get(pinCode);

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
