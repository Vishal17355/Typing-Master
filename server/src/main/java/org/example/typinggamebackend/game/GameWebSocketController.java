package org.example.typinggamebackend.game;

import org.example.typinggamebackend.game.dto.JoinRoomRequest;
import org.example.typinggamebackend.game.dto.TypingUpdateRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameWebSocketController {

    private final GameRoomService gameRoomService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameWebSocketController(GameRoomService gameRoomService, SimpMessagingTemplate messagingTemplate) {
        this.gameRoomService = gameRoomService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/room.join")
    public void joinRoom(@Payload JoinRoomRequest request) {
        GameRoom room = gameRoomService.joinRoom(request.getRoomId(), request.getUsername());
        messagingTemplate.convertAndSend("/topic/room." + request.getRoomId(), room);
    }

    @MessageMapping("/room.update")
    public void updateTyping(@Payload TypingUpdateRequest request) {
        GameRoom room = gameRoomService.updateTyping(
                request.getRoomId(),
                request.getUsername(),
                request.getProgress(),
                request.getWpm(),
                request.isFinished()
        );
        messagingTemplate.convertAndSend("/topic/room." + request.getRoomId(), room);
    }
}

