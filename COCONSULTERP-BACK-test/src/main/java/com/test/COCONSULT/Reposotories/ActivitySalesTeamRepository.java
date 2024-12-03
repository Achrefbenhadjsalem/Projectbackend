package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.DTO.ClassSalesTeam;
import com.test.COCONSULT.Entity.ActivitySalesTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitySalesTeamRepository extends JpaRepository<ActivitySalesTeam,Long> {
    List<ActivitySalesTeam> findByClassSalesTeam(ClassSalesTeam classSalesTeam);
}
