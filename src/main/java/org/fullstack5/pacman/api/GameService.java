package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.*;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface GameService {
    Flux<GameState> getState(String gameId);
    GameRegistered registerGame(Duration step, PacmanRunner pacmanRunner, GhostsRunner ghostsRunner);
    PlayerRegistered registerPlayer(String gameId, PlayerType type);
    void performMove(String gameId, String authId, Direction direction, Piece.Type type);
}
