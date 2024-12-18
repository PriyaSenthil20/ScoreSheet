package com.game.scoresheet.model;

public class Player {
    private int playerId;
    private String playerName;
    private int recentScore;
    private int playerTotalScore;
    private boolean isActive;

    public Player(int playerId, String playerName, int recentScore, int playerTotalScore) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.recentScore = recentScore;
        this.playerTotalScore = playerTotalScore;
        this.isActive = true;
    }

    public Player(int playerId, int recentScore, int totalScore) {
        this.playerId = playerId;
        this.playerName = "Player "+playerId;
        this.recentScore = recentScore;
        this.playerTotalScore = totalScore;
        this.isActive=true;
    }
    public int getPlayerId() {
        return playerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public int getRecentScore() {
        return recentScore;
    }

    public void setRecentScore(int recentScore) {
        this.recentScore = recentScore;
    }

    public int getPlayerTotalScore() {
        return playerTotalScore;
    }

    public void setPlayerTotalScore(int playerTotalScore) {
        this.playerTotalScore = playerTotalScore;
    }
}
