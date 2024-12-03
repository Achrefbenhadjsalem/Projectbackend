package com.test.COCONSULT.WebsocketConfig;

import com.test.COCONSULT.DTO.MessageType;
import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        try {
            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
            User user = (User) headerAccessor.getSessionAttributes().get("user");

            if (user != null) {
                log.info("User disconnected: {}", user.getUsername());

                // Create a leave message for the user
                Chat leaveMessage = Chat.builder()
                        .type(MessageType.LEAVE)
                        .sender(user)
                        .build();

                // Broadcast the leave message to the user's group chats
                for (GroupChat groupChat : user.getGroupChats()) {
                    messagingTemplate.convertAndSend("/topic/groupChat" + groupChat.getId(), leaveMessage);
                }
            }
        } catch (Exception e) {
            log.error("Error handling WebSocket disconnect event: {}", e.getMessage());
            // Handle the error appropriately, e.g., log it, notify admins, etc.
        }
    }
}
