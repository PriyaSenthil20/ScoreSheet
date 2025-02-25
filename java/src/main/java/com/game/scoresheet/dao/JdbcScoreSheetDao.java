package com.game.scoresheet.dao;

import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.ScoreSheet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcScoreSheetDao implements ScoreSheetDao {
    private final JdbcTemplate jdbcTemplate;
    private  final String SS_SELECT="SELECT \n" +
            "    score_id, \n" +
            "    round_id, \n" +
            "    player_id, \n" +
            "    player_name, \n" +
            "    score, \n" +
            "    note\n" +
            "FROM \n" +
            "    scores ";
    public JdbcScoreSheetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ScoreSheet> getScoreSheets() {
        List<ScoreSheet> scoresheets = new ArrayList<>();
        String sql = SS_SELECT;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        } catch (
                CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database ", e);
        }
        return scoresheets;
    }
    @Override
    public ScoreSheet getScoreSheetById(int sheetId) {
        ScoreSheet scoresheet=null;
        String str=SS_SELECT+" WHERE score_id=?";
        try{
            SqlRowSet result=jdbcTemplate.queryForRowSet(str,sheetId);
            if(result.next()){
                scoresheet=mapRowToScoreSheetForPlayers(result);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        return  scoresheet;
    }

    @Override
    public List<ScoreSheet> getScoreSheetByRoundNumber(int roundNumber) {
        List<ScoreSheet> scoresheets = new ArrayList<>();
        String sql=SS_SELECT+" WHERE round_id=?";
        try{
            SqlRowSet result=jdbcTemplate.queryForRowSet(sql,roundNumber);
            while(result.next()){
                scoresheets.add(mapRowToScoreSheetForPlayers(result));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        return  scoresheets;
    }

    @Override
    public ScoreSheet createScoreSheet(ScoreSheet scoresheet) {
        ScoreSheet scoreSheetForPlayers=null;
        String insertSql="INSERT INTO scores (round_id, player_id, player_name, score, note)\n" +
                "VALUES (?, ?, ?, ?, ?) ";
        try{
            int scoreID=jdbcTemplate.queryForObject(insertSql,
                    Integer.class, scoresheet.getRound_id(),
                    scoresheet.getPlayer_id(),
                    scoresheet.getPlayer_name(),
                    scoresheet.getScore(),scoresheet.getNote());
            scoreSheetForPlayers=getScoreSheetById(scoreID);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return scoreSheetForPlayers;
    }

    @Override
    public List<ScoreSheet> getScoreSheetByPlayerId(int playerId) {
        List<ScoreSheet> scoresheets = new ArrayList<>();
        String sql=SS_SELECT+" WHERE player_id=?";
        try{
            SqlRowSet result=jdbcTemplate.queryForRowSet(sql,playerId);
            while(result.next()){
                scoresheets.add(mapRowToScoreSheetForPlayers(result));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
        return  scoresheets;
    }
    private ScoreSheet mapRowToScoreSheetForPlayers(SqlRowSet rs) {
        ScoreSheet scoreSheet=new ScoreSheet();
        scoreSheet.setScore_id(rs.getInt("score_id"));
        scoreSheet.setRound_id(rs.getInt("round_id"));
        scoreSheet.setPlayer_id(rs.getInt("player_id"));
        scoreSheet.setScore(rs.getInt("score"));
        scoreSheet.setNote(rs.getString("note"));
        return scoreSheet;
    }

}
