package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;

import java.util.List;

public interface GroupChatInterface {

    GroupChat createGroupChat(RoleName roleName, GroupChat groupChat);

    Void addUserToGroupChatByRole(Long IdGroupChat, List<Long> IdUser);
    GroupChat removeUserFromGroupChat(GroupChat groupChat, User user);

    GroupChat updateGroupChat(Long groupId, String groupTitle, String rules);

    GroupChat deleteGroupChat( Long groupId);

    List<GroupChat> getAllGroupChatsByRole(RoleName roleName);

    GroupChat getGroupChatById( Long groupId);
    User banneduser(Long banneduser);
    User removeban(Long iduser);
}
