package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Qestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Qestion, Integer> {


}
