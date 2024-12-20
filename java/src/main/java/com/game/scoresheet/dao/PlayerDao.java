package com.game.scoresheet.dao;

import com.game.scoresheet.model.Player;

import java.util.List;

public interface PlayerDao {
    List<Player> getPlayers();
    Player getPlayerById(int playerId);
    Player createPlayer(Player player);
    boolean deletePlayer(String playerName);
}
