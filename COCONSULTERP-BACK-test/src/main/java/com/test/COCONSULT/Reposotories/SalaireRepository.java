package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Salaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaireRepository extends JpaRepository<Salaire,Integer> {
    List<Salaire> findByUser_Id(Long idUser);

}
