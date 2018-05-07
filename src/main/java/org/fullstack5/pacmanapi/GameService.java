package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.Direction;
import org.fullstack5.pacmanapi.models.response.GameRegistered;
import org.fullstack5.pacmanapi.models.response.GameState;
import reactor.core.publisher.Flux;

public interface GameService {
    Flux<GameState> getState(String gameId);
    GameRegistered register();
    void performMove(String gameId, Direction direction);
}
