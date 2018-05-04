package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.GameState;
import reactor.core.publisher.Flux;

public interface GameService {
    Flux<GameState> get();
}
