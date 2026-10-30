package org.example.typinggamebackend.game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

@Getter
public class GameRoom {

    private final String roomId;
    private final Map<String, PlayerState> players = new ConcurrentHashMap<>();

    public GameRoom(String roomId) {
        this.roomId = roomId;
    }
}

