package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.GroupChatInterface;
import com.test.COCONSULT.Reposotories.ChatRepository;
import com.test.COCONSULT.Reposotories.GroupChatRepository;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.Services.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Calendar;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor


public class GroupChatSerrviceIMP implements GroupChatInterface {
    @Autowired
    GroupChatRepository groupChatRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailSenderService mailSending;
    @Autowired
    ChatRepository chatRepository;
    @Override
    public GroupChat createGroupChat(RoleName roleName, GroupChat groupChat) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat with role " + roleName + " Notfound"));
        GroupChat existingGroupChat = groupChatRepository.findByRole(role);

        if (existingGroupChat != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group chat with role " + roleName + " already exists");
        } else {
            groupChat.setRole(role);

            return groupChatRepository.save(groupChat);
        }
    }
    public GroupChat createGP(GroupChat groupChat) {
        Optional<Role> role = roleRepository.findByName(groupChat.getRole().getName());
        if(!role.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat with role Notfound");

        }
        GroupChat existingGroupChat = groupChatRepository.findByRole(role.get());

        if (existingGroupChat != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group chat with role  already exists");
        }
            User user =userRepository.findById(groupChat.getUsers().iterator().next().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Notfound"));
            if (!user.getRoles().contains(role.get())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have the necessary role for this group chat");
            }
            if(!user.isBannedchatGP()){
                user.setAddedtoGPChat(true);
                user.getGroupChats().add(groupChat);
                userRepository.save(user);

                //groupChat.getUsers().add(user);
                groupChat.setRole(role.get());
                groupChatRepository.save(groupChat);



            } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is blocked from joining this group chat");}


        return groupChat;
    }

    @Override
    public Void addUserToGroupChatByRole(Long IdGroupChat, List<Long> IdUser) {
        GroupChat groupChat = groupChatRepository.findById(IdGroupChat)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat not found"));

        for (Long userId : IdUser) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            // Check if the user is already in the group chat
            if (groupChat.getUsers().contains(user)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is already in this group chat");
            }

            // Check if the user has the necessary role to join the group chat
            if (!user.getRoles().contains(groupChat.getRole())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have the necessary role to join this group chat");
            }

            // Check if the user is banned from joining group chats
            if (user.isBannedchatGP()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is blocked from joining this group chat");
            }

            // Set the user's attribute and save
            user.setAddedtoGPChat(true);
            userRepository.save(user);

            // Add the user to the group chat and save
            groupChat.getUsers().add(user);
        }

        // Save the group chat after adding all users
        groupChatRepository.save(groupChat);

        return null;
    }


    @Override
    public GroupChat removeUserFromGroupChat(GroupChat groupChat, User user) {
        if (!groupChat.getUsers().contains(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not a member of this group chat");
        }
        groupChat.getUsers().remove(user);
        user.setAddedtoGPChat(false);
        user.getGroupChats().remove(groupChat);
        userRepository.save(user);
        return groupChatRepository.save(groupChat);
    }

    @Override
    public GroupChat updateGroupChat(Long groupId, String groupTitle, String rules) {
        GroupChat groupChat = groupChatRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group chat not found with ID: " + groupId));

        if (groupTitle != null) {
            groupChat.setGroupTitle(groupTitle);
        }
        if (rules != null) {
            groupChat.setRules(rules);
        }

        groupChatRepository.save(groupChat);

        return groupChat;
    }



    @Override
    public List<GroupChat> getAllGroupChatsByRole(RoleName roleName) {
        return groupChatRepository.findByRoleName(roleName);
    }


    @Override
    public GroupChat getGroupChatById(Long groupId) {
        return groupChatRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group chat not found with ID: " + groupId));
    }

    @Override
    public User banneduser(Long Banneduserid) {
        User Banneduser =userRepository.findById(Banneduserid).orElse(null);
        Banneduser.setBannedchatGP(true);
        userRepository.save(Banneduser);
        String body = "Bonjour "+ Banneduser.getName() +" \n  Vous étes bani au seins du votre group chat  pendant une durée de 2 jours  \n"

                ;
        try {
            mailSending.send(Banneduser.getEmail(), "Mail de Penalité ", body);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scheduleBanRemoval(Banneduser.getId());


        return Banneduser;
    }
    private void scheduleBanRemoval(Long userId) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Remove the ban after 2 days
                removeban(userId);
                // Cancel the timer task after execution
                timer.cancel();
            }
        }, 2 * 24 * 60 * 60 * 1000); // 2 days in milliseconds
    }


    @Override
    public User removeban(Long iduser) {
        User Banneduser =userRepository.findById(iduser).orElse(null);
        Banneduser.setBannedchatGP(false);
        userRepository.save(Banneduser);

        String body = "Bonjour " + Banneduser.getName() + " \n  Vous avez été réintégré au sein de votre groupe de discussion. \n" +
                "\n Veuillez respecter les règles de votre groupe de discussion. \n Cordialement.";

        try {
            mailSending.send(Banneduser.getEmail(), "Mail de Penalité ", body);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Banneduser;
    }

    @Override
    public GroupChat deleteGroupChat(Long groupId) {
        GroupChat groupChat = groupChatRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat not found"));
            Set <User> users=groupChat.getUsers();
        List<Chat> chats = chatRepository.findAllByGroupChatId(groupId);
        for (Chat chat : chats) {
            chatRepository.delete(chat);
        }
            for (User u:users){
                u.setAddedtoGPChat(false);
                u.getGroupChats().remove(groupChat);
                userRepository.save(u);
            }
        groupChatRepository.delete(groupChat);

        return groupChat;
    }

@Transactional
    public List<GroupChat> getAllGroupChats() {
        return groupChatRepository.findAll();
    }
    public List <User>getavailableusers(){
        List <User> allusers=userRepository.findAll();
        List<User>availableusers=new ArrayList<>();
        for(User u:allusers){
            if(!u.isAddedtoGPChat())
                availableusers.add(u);

        }
        return availableusers;
    }
    public GroupChat getGroupChatByuser(Long iduser) {
        User user = userRepository.findById(iduser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return groupChatRepository.findByUsersContains(user);
    }
}
