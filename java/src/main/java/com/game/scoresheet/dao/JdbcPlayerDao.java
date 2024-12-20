package com.game.scoresheet.dao;

import com.game.scoresheet.model.Player;

import java.util.List;

public class JdbcPlayerDao implements PlayerDao{

    @Override
    public List<Player> getPlayers() {
        return null;
    }


    @Override
    public Player getPlayerById(int playerId) {
        return null;
    }

    @Override
    public Player createPlayer(Player player) {
        return null;
    }


    @Override
    public boolean deletePlayer(String playerName) {
        return false;
    }
}
