package com.game.scoresheet.dao;

import com.game.scoresheet.model.Player;

import java.util.List;

public interface PlayerDao {
    List<Player> getPlayers();
    Player getPlayerByName(String name);
    Player getPlayerById(int playerId);
    Player createPlayer(Player player);
    Player updateRecentScore(int score);
    boolean deletePlayer(String playerName);
}
