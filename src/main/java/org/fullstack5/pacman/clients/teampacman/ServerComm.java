package org.fullstack5.pacman.clients.teampacman;

import org.fullstack5.pacman.api.models.PlayerType;
import org.fullstack5.pacman.api.models.request.MoveRequest;
import org.fullstack5.pacman.api.models.request.RegisterGameRequest;
import org.fullstack5.pacman.api.models.request.RegisterPlayerRequest;
import org.fullstack5.pacman.api.models.request.StateRequest;
import org.fullstack5.pacman.api.models.response.GameRegistered;
import org.fullstack5.pacman.api.models.response.GameState;
import org.fullstack5.pacman.api.models.response.PlayerRegistered;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Static class taking care of communications from client to server.
 */
public final class ServerComm {

    private static final String IP = "localhost";
//    private static final String IP = "172.26.43.219"; // Ruben via Guest
//    private static final String IP = "172.20.10.2"; // Jesse via Mauro

    private static final String URL = "http://" + IP + ":8080";

    private static final long GAME_SPEED = 1000L;

    private ServerComm() {}

    public static PlayerRegistered registerPlayer(final String gameId, final PlayerType playerType) {
        final RegisterPlayerRequest request = new RegisterPlayerRequest(gameId, playerType);
        return WebClient.create(URL).post()
                .uri("/register-player")
                .body(BodyInserters.fromObject(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PlayerRegistered.class)
                .block();
    }

    public static void performMove(final MoveRequest request) {
        WebClient.create(URL).post()
                .uri("/perform-move")
                .body(BodyInserters.fromObject(request))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .block();
    }

    /**
     * @return the game id.
     */
    static String startGame() {
        final RegisterGameRequest request = new RegisterGameRequest(Duration.ofMillis(GAME_SPEED));
        return WebClient.create(URL).post()
                .uri("/register-game")
                .body(BodyInserters.fromObject(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GameRegistered.class)
                .block()
                .getGameId();
    }

    static Flux<GameState> establishGameStateFlux(final String gameId) {
        final StateRequest request = new StateRequest(gameId);
        return WebClient.create(URL).post()
                .uri("/current-state")
                .body(BodyInserters.fromObject(request))
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(GameState.class)
                .doOnError(throwable -> {
                    System.out.println("Stream terminated remotely. Exiting");
                    System.exit(1);
                });
    }

}
