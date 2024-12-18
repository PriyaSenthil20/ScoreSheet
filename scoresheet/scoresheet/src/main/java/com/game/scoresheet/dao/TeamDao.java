package com.game.scoresheet.dao;

import com.game.scoresheet.model.Team;

import java.util.List;

public interface TeamDao {
    List<Team> getTeams();
    Team getTeamByName(String teamName);
    Team getTeamById(int teamId);
    Team createTeam(Team team);
    Team updateRecentScore(int score);
    boolean deleteTeam(String teamName);
}
