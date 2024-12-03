package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Team;
import com.test.COCONSULT.Entity.Tickets;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.TeamInterface;
import com.test.COCONSULT.Reposotories.TeamRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TeamServiceImp implements TeamInterface {

    TeamRepository teamRepository;

    UserRepository userRepository;

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team getTeamById(Integer idTeam) {
        return teamRepository.findTeamByIdTeam(idTeam);
    }

    @Override
    public void deleteTeamById(Integer idTeam) {
        teamRepository.deleteById(idTeam);

    }

    @Override
    public Team editTeamByID(Integer idTeam, Team updateTeam) {
        Team existingTeam = teamRepository.findTeamByIdTeam(idTeam);
        if (existingTeam !=null) {
            existingTeam.setTeamLeader(updateTeam.getTeamLeader());
            existingTeam.setCapacity(updateTeam.getCapacity());
            existingTeam.setTeamName(updateTeam.getTeamName());
            return teamRepository.save(existingTeam);


        }

        System.out.println("Team dos not existe anymore");
        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team addTeamandaffecteruser(Team team, String username) {
        User user=userRepository.findByUsername(username).orElse(null);
        teamRepository.save(team);
        user.setTeam(team);


        return null;
    }

    @Override
    public void affeteruserAteam(Integer idTeam, String username) {
        Team team=teamRepository.findTeamByIdTeam(idTeam);
        User user=userRepository.findByUsername(username).orElse(null);
        user.setTeam(team);
        teamRepository.save(team);



    }


}
