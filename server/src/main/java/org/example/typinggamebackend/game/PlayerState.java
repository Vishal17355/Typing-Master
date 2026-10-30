package org.example.typinggamebackend.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerState {

    private String username;
    private double progress; // 0.0 - 1.0
    private double wpm;
    private boolean finished;
}

