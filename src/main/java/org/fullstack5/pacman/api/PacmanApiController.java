package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.request.RegisterPlayerRequest;
import org.fullstack5.pacman.api.models.request.StateRequest;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class PacmanApiController {

    @Autowired
    GameService service;

    @CrossOrigin
    @PostMapping(path = "/current-state", produces = "application/stream+json")
    public Flux<GameState> getCurrentState(
            @RequestBody StateRequest request) {
        return service.getState(request.getGameId());
    }

    @CrossOrigin
    @GetMapping(path = "/register-game")
    public GameRegistered registerGame() {
        return service.registerGame();
    }

    @CrossOrigin
    @PostMapping(path= "/register-player")
    public PlayerRegistered registerPlayer(
            @RequestBody RegisterPlayerRequest request) {
        return service.registerPlayer(request.getGameId(), request.getType());
    }

    @CrossOrigin
    @PostMapping(path = "/perform-move")
    public void performMove(
            @RequestBody MoveRequest request) {
        service.performMove(request.getGameId(), request.getDirection(), request.getType());
    }
}
