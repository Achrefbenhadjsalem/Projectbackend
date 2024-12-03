package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.ServiceIMP.ChatServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/Chat")
public class ChatControllerr {
    @Autowired
    ChatServiceIMP chatserviceimp;
    @PostMapping("/sendChat/{idUser}/{idgroup}/{messageContent}")

    public Chat sendChat(@PathVariable ("idUser")  Long sender,@PathVariable ("idgroup") Long groupChat,@PathVariable("messageContent") String messageContent) {
        return chatserviceimp.sendChat(sender, groupChat, messageContent);
    }
@GetMapping("/getAlllChatsByUser/{idUser}")
    public Optional<Chat> getAlllChatsByUser(@PathVariable ("idUser") Long idUser) {
        return chatserviceimp.getAlllChatsByUser(idUser);
    }
@GetMapping("/getAllchatsPerGroup/{idgroupChat}")
    public List<Chat> getAllchatsPerGroup(@PathVariable ("idgroupChat")  Long idgroupChat) {
        return chatserviceimp.getAllchatsPerGroup(idgroupChat);
    }
@PostMapping("/broadcastMSG/{message}")
    public void broadcastMessageToGroupChats(@PathVariable ("message") String message) {
        chatserviceimp.broadcastMessageToGroupChats(message);
    }

    public Void AddDocs() {
        return chatserviceimp.AddDocs();
    }

    public Void AddImg() {
        return chatserviceimp.AddImg();
    }
}
