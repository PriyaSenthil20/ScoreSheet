package com.game.scoresheet.dao;

import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.Rounds;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRoundDao implements RoundDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcRoundDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final String ROUND_SELECT="SELECT round_id, game_id, round_number, round_name FROM rounds  ";

    @Override
    public List<Rounds> getRounds() {
        List<Rounds> rounds=new ArrayList<>();
        String sql=ROUND_SELECT;
        try{
            SqlRowSet results=jdbcTemplate.queryForRowSet(sql);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database ",e);
        }
        return rounds;
    }

    @Override
    public Rounds getRoundById(int roundId) {
        Rounds round = null;
        String sql =ROUND_SELECT +" WHERE round_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, roundId);
            if (results.next()) {
                round = mapRowToRound(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return round;
    }
    @Override
    public Rounds getRoundByNumber(int roundNumber) {
        Rounds round = null;
        String sql =ROUND_SELECT +" WHERE round_number = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, roundNumber);
            if (results.next()) {
                round = mapRowToRound(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return round;
    }
    @Override
    public Rounds createRound(Rounds newRound) {
        Rounds round = null;
        if(newRound.getRound_name()==null||newRound.getRound_name().isEmpty()){
            newRound.setRound_name("NewRound"+newRound.getRound_number());
        }
        String insertSql = "INSERT INTO public.rounds(\n" +
                "\t game_id, round_number, round_name)\n" +
                "\tVALUES (?, ?, ?) RETURNING round_id";

        try {
            int newRoundId=jdbcTemplate.queryForObject(insertSql,Integer.class, newRound.getGame_id(),newRound.getRound_number(),newRound.getRound_name());
            round= getRoundById(newRoundId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return round;
    }
    private Rounds mapRowToRound(SqlRowSet rs) {
        Rounds round=new Rounds();
        round.setRound_id(rs.getInt("round_id"));
        round.setGame_id(rs.getInt("game_id"));
        round.setRound_number(rs.getInt("round_number"));
        round.setRound_name(rs.getString("round_name"));
        return round;
    }
}
