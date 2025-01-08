package com.game.scoresheet.dao;

import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.Game;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final String GAME_SELECT="SELECT game_id,game_name,game_date FROM games ";

    @Override
    public List<Game> getGames() {
        List<Game> games=new ArrayList<>();
        String sql=GAME_SELECT;
        try{
            SqlRowSet results=jdbcTemplate.queryForRowSet(sql);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database ",e);
        }
        return games;
    }

    @Override
    public Game getGameById(int gameId) {
        Game game = null;
        String sql =GAME_SELECT +" WHERE game_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
            if (results.next()) {
                game = mapRowToGame(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return game;
    }

    @Override
    public Game createGame(Game newGame) {
        Game game = null;
        if(newGame.getGame_name()==null||newGame.getGame_name().isEmpty()){
            newGame.setGame_name("NewGame");
        }
        String insertGameSql = "INSERT INTO public.games(" +
                " game_name, game_date) " +
                " VALUES ( ?, ?) RETURNING game_id";

        try {
            int newGameId=jdbcTemplate.queryForObject(insertGameSql,Integer.class, newGame.getGame_name(),newGame.getGame_date());
            game= getGameById(newGameId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return game;
    }

    @Override
    public boolean deleteGame(int gameId) {
        return false;
    }
    private Game mapRowToGame(SqlRowSet rs) {
        Game game=new Game();
        game.setGame_id(rs.getInt("game_id"));
        game.setGame_name(rs.getString("game_name"));
        game.setGame_date(rs.getString("game_date"));
        return game;
    }
    }
