package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.DTO.ProspectStatus;
import com.test.COCONSULT.Entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect,Long> {
   // List<Prospect> findByStatusAndLastStatusUpdateBefore(ProspectStatus status, LocalDateTime timestamp);
}