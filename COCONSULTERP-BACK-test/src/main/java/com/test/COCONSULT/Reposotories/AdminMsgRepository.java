package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.AdminMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdminMsgRepository extends JpaRepository<AdminMsg, Long> {
}
