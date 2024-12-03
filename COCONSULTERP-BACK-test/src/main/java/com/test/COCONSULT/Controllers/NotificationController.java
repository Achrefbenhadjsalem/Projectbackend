package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Notification;
import com.test.COCONSULT.Interfaces.NotificatinInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Notification")
public class NotificationController {
    @Autowired
    NotificatinInterface NotificatinInterface;
@PostMapping("/createNotification")
    public Notification createNotification(@RequestBody Notification notification) {
        return NotificatinInterface.createNotification(notification);
    }
@GetMapping("/getNotification/{id}")
    public Notification getNotification(@PathVariable("id") Long id) {
        return NotificatinInterface.getNotification(id);
    }
@DeleteMapping("/deleteNotification/{id}")
    public void deleteNotification(@PathVariable ("id") Long id) {
        NotificatinInterface.deleteNotification(id);
    }
@GetMapping("/getAllNotifications")
    public List<Notification> getAllNotifications() {
        return NotificatinInterface.getAllNotifications();
    }
@GetMapping("/getNotificationByuser/{id}")
    public List<Notification> getNotificationByuser(@PathVariable ("id") Long id) {
        return NotificatinInterface.getNotificationByuser(id);
    }
}
