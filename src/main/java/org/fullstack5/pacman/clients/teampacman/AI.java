package org.fullstack5.pacman.clients.teampacman;

import org.fullstack5.pacman.api.models.response.GameState;

public interface AI {

    void runAI(final GameState gameState);
}
