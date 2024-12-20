package com.game.scoresheet.dao;

import com.game.scoresheet.model.Game;
import java.util.List;

public interface GameDao {
    public List<Game> getGames();
    public Game getGameById(int gameId);
    public Game createGame(Game newGame);
    public boolean deleteGame(int gameId);
}
