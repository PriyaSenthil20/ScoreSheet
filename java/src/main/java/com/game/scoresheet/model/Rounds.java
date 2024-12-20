package com.game.scoresheet.model;

public class Rounds {
    private int round_id ;
    private int game_id;
    private int round_number;
    private String round_name;

    public Rounds() {
    }

    public Rounds(int round_id, int game_id, int round_number, String round_name) {
        this.round_id = round_id;
        this.game_id = game_id;
        this.round_number = round_number;
        this.round_name = round_name;
    }

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getRound_number() {
        return round_number;
    }

    public void setRound_number(int round_number) {
        this.round_number = round_number;
    }

    public String getRound_name() {
        return round_name;
    }

    public void setRound_name(String round_name) {
        this.round_name = round_name;
    }
}
