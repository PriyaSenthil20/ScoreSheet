package com.game.scoresheet.model;

public class Player {
    private int playerId;
    private String playerName;
    private int game_id;
    private boolean isActive;

    public Player() {
    }

    public Player(int playerId, String playerName, int game_id, boolean isActive) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.game_id = game_id;
        this.isActive = isActive;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
