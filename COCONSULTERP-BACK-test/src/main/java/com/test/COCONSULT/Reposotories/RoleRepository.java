package com.test.COCONSULT.Reposotories;



import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName );

}
