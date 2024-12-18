package com.game.scoresheet.model;


import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class Team {
    private int teamId;
    private String teamName;
    private List<Player> playersList=new ArrayList<>();
    private int teamTotal;
    private boolean isActive;

    public Team(int teamId, String teamName, List<Player> playersList, int teamTotal) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.playersList = playersList;
        this.teamTotal = teamTotal;
        this.isActive = true;
    }

    public Team(int teamId, List<Player> playersList, int teamTotal) {
        this.teamId = teamId;
        this.playersList = playersList;
        this.teamTotal = teamTotal;
        this.teamName = "Team "+teamId;
        this.isActive=true;

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public int getTeamTotal() {
        return teamTotal;
    }

    public void setTeamTotal(int teamTotal) {
        this.teamTotal = teamTotal;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
