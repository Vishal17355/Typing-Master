package org.example.typinggamebackend.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRoomRequest {

    private String roomId;
    private String username;
}

