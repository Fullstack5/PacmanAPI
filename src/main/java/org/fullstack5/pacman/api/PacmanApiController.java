package org.fullstack5.pacman.api;

import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.request.RegisterGameRequest;
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
    public GameRegistered registerDefaultGame() {
        RegisterGameRequest request = new RegisterGameRequest();
        return service.registerGame(
                request.getStepDuration(),
                request.getPacmanRunner(),
                request.getGhostsRunner());
    }

    @CrossOrigin
    @PostMapping(path = "/register-game")
    public GameRegistered registerGame(
            @RequestBody RegisterGameRequest request) {
        return service.registerGame(
                request.getStepDuration(),
                request.getPacmanRunner(),
                request.getGhostsRunner());
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
        System.out.println(String.format("/perform-move called with %s %s", request.getType(), request.getDirection()));
        service.performMove(
                request.getGameId(),
                request.getAuthId(),
                request.getDirection(),
                request.getType());
    }
}
