package com.test.COCONSULT.Reposotories;



import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);

    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRolesContains(Role role);
    @Query("SELECT u.createdDate, COUNT(u) FROM User u GROUP BY u.createdDate")
    List<Object[]> countUsersPerDay();

    @Query("SELECT r.name, COUNT(u) FROM User u JOIN u.roles r GROUP BY r.name")
    List<Object[]> countUsersByRoles();
    //List<User> findByRolesContaining(Role );

    //User findUserById(Long id);
    @Query("SELECT u FROM User u JOIN u.meetings m WHERE m.idMeeting = :idMeeting")
    List<User> findUsersByMeetingId(@Param("idMeeting") Integer idMeeting);

    /*@Query("SELECT u FROM User u JOIN u.bilan b ORDER BY b.sum_total ASC")
    List<User> findUsersOrderByBilanSum_totalAsc();
    @Query("SELECT u FROM User u INNER JOIN u.roles r LEFT JOIN u.bilan b WHERE r.name = :roleName ORDER BY b.sum_total ASC")
    List<User> findAllUserByRoleOrderSum_total(@Param("roleName") RoleName roleName);*/

}
