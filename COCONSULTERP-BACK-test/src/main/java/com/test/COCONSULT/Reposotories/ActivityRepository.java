package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {

    Activity findActivityByIdActivity(Integer idActivity);


    void deleteActivityByIdActivity(Integer idActivity);
}
