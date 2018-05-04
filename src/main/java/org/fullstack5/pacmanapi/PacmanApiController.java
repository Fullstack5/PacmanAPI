package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PacmanApiController {

    @Autowired
    GameService service;

    @GetMapping(path = "/gameState", produces = "application/stream+json")
    public Flux<GameState> getGameState() {
        return service.get();
    }
}
