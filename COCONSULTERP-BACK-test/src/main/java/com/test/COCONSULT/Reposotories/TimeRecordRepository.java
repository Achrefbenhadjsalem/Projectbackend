package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
    List<TimeRecord> findByProjetsIdProjet(Long projectId);
    List<TimeRecord> findTimeRecordByProjetsIdProjet(Long userId);

    @Query("SELECT COUNT(t) FROM TimeRecord t WHERE t.projets.idProjet = :projectId AND t.duration = :duration")
    long countByProjectIdAndDuration(Long projectId, LocalDate duration);

    @Modifying
    @Transactional
    @Query("UPDATE TimeRecord t SET t.projets.idProjet = :projectId WHERE t.idTimeRec = :timeRecordId")
    void assignTimeRecordToProject(@Param("timeRecordId") Long timeRecordId, @Param("projectId") Long projectId);
}