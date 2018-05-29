package org.fullstack5.pacman.api.models.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

/**
 * Class for the request data for registering a game
 */
@Getter
@Setter
public class RegisterGameRequest {
    // can be an int or float (in seconds)
    // or a string of the format "PT0.2S"
    private Duration stepDuration = Duration.ofSeconds(1);
}
