package com.game.scoresheet.model;

public class Game {
    private int game_id;
    private String game_name;
    private String game_date;


    public Game(){}
    public Game(int game_id, String game_name, String game_date) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.game_date = game_date;

    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }

}
