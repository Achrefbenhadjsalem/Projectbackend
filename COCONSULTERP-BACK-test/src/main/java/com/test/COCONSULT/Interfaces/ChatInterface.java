package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;

import java.util.List;
import java.util.Optional;

public interface ChatInterface {
 Chat sendChat(Long senderid, Long groupChatid, String messageContent);

 Optional<Chat> getAlllChatsByUser(Long idUser);
List<Chat> getAllchatsPerGroup(Long groupChat);
 void broadcastMessageToGroupChats(String message);
 Void AddDocs();
 Void AddImg();
 List<User>getAllConnectedUsers();
 List<User>getAllConUserGRP(Long idGroupChat);
}
