package org.fullstack5.pacman.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fullstack5.pacman.api.models.GhostsRunner;
import org.fullstack5.pacman.api.models.PacmanRunner;

import java.time.Duration;

/**
 * Class for the request data for registering a game
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterGameRequest {
    // can be an int or float (in seconds)
    // or a string of the format "PT0.2S"
    private Duration stepDuration = Duration.ofSeconds(1);

    private GhostsRunner ghostsRunner;
    private PacmanRunner pacmanRunner;
}
