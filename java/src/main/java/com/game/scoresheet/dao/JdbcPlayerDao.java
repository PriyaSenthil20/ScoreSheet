package com.game.scoresheet.dao;

import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.Game;
import com.game.scoresheet.model.Player;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPlayerDao implements PlayerDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final String PLAYER_SELECT="SELECT player_id, game_id, player_name, is_active FROM players ";

    @Override
    public List<Player> getPlayers() {
        List<Player> players=new ArrayList<>();
        String sql=PLAYER_SELECT;
        try{
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql);
        }catch (
        CannotGetJdbcConnectionException e){
        throw new DaoException("Unable to connect to server or database ",e);
        }
        return players;
    }


    @Override
    public Player getPlayerById(int playerId) {
        Player player = null;
        String sql =PLAYER_SELECT +" WHERE player_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playerId);
            if (results.next()) {
                player = mapRowToPlayer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return player;
    }

    @Override
    public Player createPlayer(Player newPlayer) {
        Player player = null;
        if(newPlayer.getPlayerName()==null||newPlayer.getPlayerName().isEmpty()){
            newPlayer.setPlayerName("Player "+newPlayer.getPlayerId());
        }
        String insertPlayerSql = "INSERT INTO public.players(" +
                " player_id,game_id,player_name,is_active) " +
                " VALUES ( ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(insertPlayerSql,newPlayer.getPlayerId(),newPlayer.getPlayerName(),newPlayer.getGame_id(),newPlayer.isActive());
            player=getPlayerById(newPlayer.getPlayerId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return player;
    }


    @Override
    public boolean deletePlayer(Player player) {
            String insertPlayerSql = "UPDATE public.players\n" +
                    "\tSET is_active= ? \n" +
                    "\tWHERE player_id= ?;";

            try {
                jdbcTemplate.update(insertPlayerSql,player.isActive(),player.getPlayerId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
            return true;
    }

    private Player mapRowToPlayer(SqlRowSet rs) {
        Player player=new Player();
        player.setPlayerId(rs.getInt("player_id"));
        player.setGame_id(rs.getInt("game_id"));
        player.setPlayerName(rs.getString("player_name"));
        player.setActive(rs.getBoolean("is_active"));
        return player;
    }
}
