package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.AdminMsg;
import com.test.COCONSULT.Entity.Notification;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.AdminMsgInterface;
import com.test.COCONSULT.Reposotories.AdminMsgRepository;
import com.test.COCONSULT.Reposotories.NotificationRepository;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminMsgServiceIMP implements AdminMsgInterface {
    @Autowired
    private AdminMsgRepository adminMsgRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public void createmsg(AdminMsg adminMsg) {
        // You might want to add some validation or processing logic here before saving
        adminMsgRepository.save(adminMsg);
    }

    public void deletemsg(Long id) {
        adminMsgRepository.deleteById(id);
    }

    public AdminMsg getmsg(Long id) {
        Optional<AdminMsg> adminMsgOptional = adminMsgRepository.findById(id);
        return adminMsgOptional.orElse(null);
    }

    public List<AdminMsg> getallmsg() {
        return adminMsgRepository.findAll();
    }

    public void sendmsg(Long idadminMsg, List<Long> idUsers) {
        AdminMsg adminMsg = getmsg(idadminMsg);
        if (adminMsg == null) {
            // Handle the case where the admin message with the given ID does not exist
            return;
        }
        for(Long idUser:idUsers){

        }
    }
    public void sendNotification(Long adminMsgId, String Title, String s, List<String>  recipients) {
        // Create a new notification
        // Update the flag in AdminMsg indicating that notification has been sent
        Notification notification = new Notification();
        Optional<AdminMsg> adminMsgOptional = adminMsgRepository.findById(adminMsgId);
        List <User> receptientsList=new ArrayList<>();
        for (String rol : recipients) {
            Role role = roleRepository.findByName(RoleName.valueOf(rol))
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            List<User> listusers = userRepository.findByRolesContains(role);
                receptientsList.addAll(listusers);


        }
        notification.setRecipients(receptientsList);

        notification.setDate(new Date());
        notification.setTitle(Title);
        notification.setMessage(s);
        notification.setAdminMsgId(adminMsgOptional.get());

        if(adminMsgOptional.isPresent()){
            AdminMsg adminMsg = adminMsgOptional.get();
            adminMsg.setNotificationSent(true);
            adminMsgRepository.save(adminMsg);
        }
        // Save the notification
        notificationRepository.save(notification);

    }
}
