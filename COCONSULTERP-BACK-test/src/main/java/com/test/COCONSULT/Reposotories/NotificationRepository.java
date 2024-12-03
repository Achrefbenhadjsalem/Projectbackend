package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.AdminMsg;
import com.test.COCONSULT.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
