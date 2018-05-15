package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Piece;
import org.fullstack5.pacman.api.models.PlayerType;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import reactor.core.publisher.Flux;

public interface GameService {
    Flux<GameState> getState(String gameId);
    GameRegistered registerGame();
    PlayerRegistered registerPlayer(String gameId, PlayerType type);
    void performMove(String gameId, String authId, Direction direction, Piece.Type type);
}
