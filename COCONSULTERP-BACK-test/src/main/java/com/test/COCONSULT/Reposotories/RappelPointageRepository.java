package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.RappelPointage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RappelPointageRepository extends JpaRepository<RappelPointage, Integer> {
    RappelPointage findByUser_Id(Long user_id);
}
