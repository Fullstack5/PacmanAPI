package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.request.CurrentStateRequest;
import org.fullstack5.pacmanapi.models.response.GameRegistered;
import org.fullstack5.pacmanapi.models.response.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PacmanApiController {

    @Autowired
    GameService service;

    @PostMapping(path = "/current-state", produces = "application/stream+json")
    public Flux<GameState> getCurrentState(
            @RequestBody CurrentStateRequest request) {
        return service.getState(request.getGameId());
    }

    @GetMapping(path = "/register-game")
    public GameRegistered registerGame() {
        return service.register();
    }
}
