package org.fullstack5.pacmanapi;

import org.fullstack5.pacmanapi.models.GameState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class PacmanApiController {

    @GetMapping(path = "/gameState", produces = "application/stream+json")
    public Flux<GameState> getGameState() {
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(GameState::new);
    }
}
