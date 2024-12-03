package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.MessageType;
import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.ChatInterface;
import com.test.COCONSULT.Reposotories.ChatRepository;
import com.test.COCONSULT.Reposotories.GroupChatRepository;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@AllArgsConstructor

public class ChatServiceIMP implements ChatInterface {
    @Autowired
    GroupChatRepository groupChatRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    SimpMessagingTemplate messagingTemplate;




    @Override
    public Chat sendChat(Long idSender, Long groupChatId, String messageContent) {
        // Find the sender user
        User sender = userRepository.findById(idSender)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found"));

        // Find the group chat
        GroupChat groupChat = groupChatRepository.findById(groupChatId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat not found"));

        // Check if the sender is a member of the group chat
        if (!groupChat.getUsers().contains(sender)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sender is not a member of the group chat");
        }
        if(sender.isBannedchatGP()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sender is banned from the group chat");
        }

        // Create a new chat message
        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setGroupChat(groupChat);
        chat.setMessage(messageContent);
        chat.setDate(new Date());
        chat.setType(MessageType.CHAT);

        // Save the chat message
        Chat savedChat = chatRepository.save(chat);

     /*   // Broadcast the chat message to all subscribed clients of the group chat
        String destination = "/topic/groupChat/" + groupChatId;
        messagingTemplate.convertAndSend(destination, savedChat);*/
        return savedChat;
    }

    @Override
    public Optional<Chat> getAlllChatsByUser(Long idUser) {
        return chatRepository.findAllBySenderId(idUser);
    }

    @Override
    public List<Chat> getAllchatsPerGroup(Long idgroupChat) {
        return chatRepository.findAllByGroupChatId(idgroupChat);
    }


    @Override
    public void broadcastMessageToGroupChats(String message) {
        Set<Long> broadcastedGroupChats=new HashSet<>();

        List<Chat> chats = chatRepository.findAll();
        for (Chat chat : chats) {
            GroupChat groupChat = chat.getGroupChat();
            if (groupChat != null && !broadcastedGroupChats.contains(groupChat.getId())) {
                String destination = "/topic/groupChat/" + groupChat.getId();
                messagingTemplate.convertAndSend(destination, message);
                broadcastedGroupChats.add(groupChat.getId());
            }
        }
    }

    @Override
    public Void AddDocs() {
        return null;
    }

    @Override
    public Void AddImg() {
        return null;
    }

    @Override
    public List<User> getAllConnectedUsers() {
        return null;
    }

    @Override
    public List<User> getAllConUserGRP(Long idGroupChat) {
        return null;
    }
}
