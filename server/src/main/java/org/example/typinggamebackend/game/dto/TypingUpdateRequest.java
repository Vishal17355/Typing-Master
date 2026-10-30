package org.example.typinggamebackend.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypingUpdateRequest {

    private String roomId;
    private String username;
    private double progress; // 0.0 - 1.0
    private double wpm;
    private boolean finished;
}

