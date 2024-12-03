package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.ServiceIMP.GroupChatSerrviceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/GroupChat")
public class GroupChatController {
    @Autowired
    GroupChatSerrviceIMP groupchatserrviceimp;
@PostMapping("/createGroupChat/{roleName}")
    public GroupChat createGroupChat(@PathVariable ("roleName") RoleName roleName,@RequestBody GroupChat groupChat) {
        return groupchatserrviceimp.createGroupChat(roleName, groupChat);
    }
@PostMapping ("/createGP")
    public GroupChat createGP(@RequestBody GroupChat groupChat) {
        return groupchatserrviceimp.createGP(groupChat);
    }
@PutMapping("/addUserToGroupChatByRole/{IdGroupChat}/{IdUser}")
    public Void addUserToGroupChatByRole(@PathVariable Long IdGroupChat,@PathVariable List <Long>  IdUser) {
        return groupchatserrviceimp.addUserToGroupChatByRole(IdGroupChat,IdUser );
    }
@DeleteMapping("/removeUserFromGroupChat")
    public GroupChat removeUserFromGroupChat(@RequestBody GroupChat groupChat,@RequestBody User user) {
        return groupchatserrviceimp.removeUserFromGroupChat(groupChat, user);
    }
@PostMapping("/updateGroupChat/{groupId}/{groupTitle}/{rules}")
    public GroupChat updateGroupChat(@PathVariable("groupId") Long groupId, @PathVariable ("groupTitle") String groupTitle,@PathVariable ("rules") String rules) {
        return groupchatserrviceimp.updateGroupChat(groupId, groupTitle, rules);
    }
@GetMapping("/getAllGroupChatsByRole/{roleName}")
    public List<GroupChat> getAllGroupChatsByRole(@PathVariable ("roleName") RoleName roleName) {
        return groupchatserrviceimp.getAllGroupChatsByRole(roleName);
    }
@GetMapping("/getAllGroupChats")
    public List<GroupChat> getAllGroupChats() {
        return groupchatserrviceimp.getAllGroupChats();
    }
    @GetMapping("/getGroupChatById/{groupId}")

    public GroupChat getGroupChatById(@PathVariable ("groupId") Long groupId) {
        return groupchatserrviceimp.getGroupChatById(groupId);
    }
    @PostMapping("/banneduser/{banneduser}")

    public User banneduser(@PathVariable ("banneduser") Long banneduser) {
        return groupchatserrviceimp.banneduser( banneduser);
    }
@DeleteMapping("/deleteGroupChat/{groupId}")
    public GroupChat deleteGroupChat(@PathVariable ("groupId") Long groupId) {
        return groupchatserrviceimp.deleteGroupChat(groupId);
    }
    @PostMapping ("/removeban/{iduser}")

    public User removeban(@PathVariable ("iduser") Long iduser) {
        return groupchatserrviceimp.removeban(iduser);
    }
  @GetMapping("/getavailableusers")
    public List<User>getavailableusers(){return groupchatserrviceimp.getavailableusers();}

    @GetMapping("/getGroupChatByuser/{iduser}")
    public GroupChat getGroupChatByuser(@PathVariable ("iduser") Long iduser) {
        return groupchatserrviceimp.getGroupChatByuser(iduser);
    }
}
