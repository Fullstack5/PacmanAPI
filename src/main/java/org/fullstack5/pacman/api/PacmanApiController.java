package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.request.StateRequest;
import org.fullstack5.pacman.api.models.response.GameRegistered;
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
        return service.register();
    }

    @CrossOrigin
    @PostMapping(path = "/perform-move")
    public void performMove(
            @RequestBody MoveRequest request) {
        service.performMove(request.getGameId(), request.getDirection(), request.getType());
    }
}
