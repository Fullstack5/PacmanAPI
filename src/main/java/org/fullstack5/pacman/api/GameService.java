package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import reactor.core.publisher.Flux;

public interface GameService {
    Flux<GameState> getState(String gameId);
    GameRegistered register();
    void performMove(String gameId, Direction direction, Piece.Type type);
}
