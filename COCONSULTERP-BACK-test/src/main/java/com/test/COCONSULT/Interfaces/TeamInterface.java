package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Team;
import com.test.COCONSULT.Entity.Tickets;

import java.util.List;

public interface TeamInterface {

    Team addTeam(Team team);

    Team getTeamById(Integer idTeam);

    void deleteTeamById(Integer idTeam);

    Team editTeamByID(Integer idTeam, Team updateTeam);

    public List<Team> getAllTeams();

    Team addTeamandaffecteruser(Team team, String username);

    void affeteruserAteam(Integer idTeam, String username);








}
