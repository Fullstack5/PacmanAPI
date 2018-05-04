package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.GameState;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class GameServiceImpl implements GameService {
    private ConnectableFlux<GameState> gameFlux;

    public GameServiceImpl() {
        gameFlux = Flux
            .interval(Duration.ofSeconds(1))
            .map(GameState::new)
            .publish();
        gameFlux.connect();
    }

    @Override
    public Flux<GameState> get() {
        gameFlux.subscribe();
        return gameFlux;
    }
}
