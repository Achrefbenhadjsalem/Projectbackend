package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.MessageType;
import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatWSController {
    private static final Logger logger = LoggerFactory.getLogger(ChatWSController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    ChatRepository chatRepository;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage(
            @Payload Chat chatMessage
    ) {         logger.info("Received message: {}", chatMessage);
        boolean existed = false;
        User sender = chatMessage.getSender();
       // User sender = chatMessage?.getSender();
        if (sender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found");
        }

        GroupChat groupChat = chatMessage.getGroupChat();

        if(groupChat==null) {
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat not found");}

        // Check if the sender is a member of the group chat
        for ( User u : groupChat.getUsers() ){
            if (u.getId() == sender.getId()){
                existed = true;
                break;
            }

        }
        if (existed==false){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sender is not a member of the group chat");
        }

        if(sender.isBannedchatGP()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sender is banned from the group chat");
        }


       // chatMessage.setType(MessageType.CHAT);

        // Save the chat message
        chatRepository.save(chatMessage);


        Long groupChatId = chatMessage.getGroupChat().getId();
        // Construct the message payload including sender information
        Map<String, Object> messagePayload = new HashMap<>();
        messagePayload.put("content", chatMessage.getMessage());
        messagePayload.put("sender", chatMessage.getSender().getUsername());
        messagePayload.put("time", chatMessage.getDate());

        // Broadcast the message to the topic corresponding to the group chat ID
        messagingTemplate.convertAndSend("/topic/groupChat/" + groupChatId, messagePayload);

    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Chat addUser(
            @Payload Chat chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
