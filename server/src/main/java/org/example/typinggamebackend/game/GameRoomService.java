package org.example.typinggamebackend.game;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class GameRoomService {

    private final Map<String, GameRoom> rooms = new ConcurrentHashMap<>();
    private static final int MAX_PLAYERS_PER_ROOM = 2;

    public synchronized GameRoom joinRoom(String roomId, String username) {
        GameRoom room = rooms.computeIfAbsent(roomId, GameRoom::new);

        if (room.getPlayers().size() >= MAX_PLAYERS_PER_ROOM && !room.getPlayers().containsKey(username)) {
            throw new IllegalStateException("Room is full");
        }

        room.getPlayers().putIfAbsent(username, new PlayerState(username, 0.0, 0.0, false));
        return room;
    }

    public GameRoom updateTyping(String roomId, String username, double progress, double wpm, boolean finished) {
        GameRoom room = rooms.computeIfAbsent(roomId, GameRoom::new);
        PlayerState current = room.getPlayers().getOrDefault(username, new PlayerState(username, 0.0, 0.0, false));
        current.setProgress(progress);
        current.setWpm(wpm);
        current.setFinished(finished);
        room.getPlayers().put(username, current);
        return room;
    }

    public Optional<GameRoom> findRoom(String roomId) {
        return Optional.ofNullable(rooms.get(roomId));
    }
}

