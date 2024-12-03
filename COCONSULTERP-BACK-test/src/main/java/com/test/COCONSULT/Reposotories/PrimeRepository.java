package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Prime;
import com.test.COCONSULT.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrimeRepository extends JpaRepository<Prime,Integer> {
    List<Prime> findByUser(User user);
}
