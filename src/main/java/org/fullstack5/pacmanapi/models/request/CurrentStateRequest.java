package org.fullstack5.pacmanapi.models.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CurrentStateRequest {
    private String gameId;
}
