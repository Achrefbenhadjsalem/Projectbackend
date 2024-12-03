package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Team;
import com.test.COCONSULT.Interfaces.TeamInterface;
import com.test.COCONSULT.Reposotories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Team")
@CrossOrigin("*")
public class TeamControlleur {
    @Autowired
    TeamInterface teamInterface;
    @PostMapping("/addTeam")
    public Team addTeam(@RequestBody Team team) {
        return teamInterface.addTeam(team);
    }
    @GetMapping("/getTeamByID/{idTeam}")
    public Team getTeamById(@PathVariable("idTeam") Integer idTeam) {
        return teamInterface.getTeamById(idTeam);
    }
    @DeleteMapping("/deleteTeamById/{idTeam}")
    public void deleteTeamById(@PathVariable("idTeam") Integer idTeam) {
        teamInterface.deleteTeamById(idTeam);
    }
    @PutMapping("/editTeamByID/{idTeam}")
    public Team editTeamByID(@PathVariable("idTeam") Integer idTeam,@RequestBody Team updateTeam) {
        return teamInterface.editTeamByID(idTeam, updateTeam);
    }
    @GetMapping("/getAllTeams")
    public List<Team> getAllTeams() {
        return teamInterface.getAllTeams();
    }

    @PutMapping("/addTeamandaffecteruser/{username}")
    public Team addTeamandaffecteruser(@RequestBody Team team,@PathVariable("username") String username) {
        return teamInterface.addTeamandaffecteruser(team, username);
    }
}
