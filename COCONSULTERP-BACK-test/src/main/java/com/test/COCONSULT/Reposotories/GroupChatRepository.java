package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Chat;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.COCONSULT.Entity.GroupChat;

import java.util.List;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
    // You can define custom query methods here if needed
    GroupChat findByRole(Role Role);

    List<GroupChat> findByRoleName(RoleName roleName);

    GroupChat findByUsersContains(User user);

    //  GroupChat findByRoleId(RoleName roleName);
}
