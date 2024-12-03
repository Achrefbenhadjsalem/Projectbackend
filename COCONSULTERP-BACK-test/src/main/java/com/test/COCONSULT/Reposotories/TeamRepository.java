package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Team;
import com.test.COCONSULT.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findTeamByIdTeam(Integer idteam);

    Team findTeamByTeamName(String teamName);



}
