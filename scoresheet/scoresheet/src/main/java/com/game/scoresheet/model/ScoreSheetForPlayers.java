package com.game.scoresheet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreSheetForPlayers {
    private int gameNumber;
    private List<Player> playersList=new ArrayList<>();
    private Map<Integer,Player> finalPlayersList=new HashMap<>();

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public Map<Integer, Player> getFinalPlayersList() {
        return finalPlayersList;
    }

    public void setFinalPlayersList(Map<Integer, Player> finalPlayersList) {
        this.finalPlayersList = finalPlayersList;
    }
}
