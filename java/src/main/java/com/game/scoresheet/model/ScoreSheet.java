package com.game.scoresheet.model;

public class ScoreSheet {
        private int score_id;
        private int round_id;
        private int player_id;
        private String player_name;
        private int score;
        private String note;

    public ScoreSheet(int score_id, int round_id, int player_id, String player_name, int score, String note) {
        this.score_id = score_id;
        this.round_id = round_id;
        this.player_id = player_id;
        this.player_name = player_name;
        this.score = score;
        this.note = note;
    }

    public ScoreSheet() {
    }

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
