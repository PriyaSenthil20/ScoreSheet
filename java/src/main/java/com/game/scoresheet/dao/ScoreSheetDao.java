package com.game.scoresheet.dao;

import com.game.scoresheet.model.ScoreSheet;

import java.util.List;

public interface ScoreSheetDao {
    public List<ScoreSheet> getScoreSheets();
    public ScoreSheet getScoreSheetById(int sheetId);
    public List<ScoreSheet> getScoreSheetByRoundNumber(int roundNumber);
    public ScoreSheet createScoreSheet(ScoreSheet scoreSheet);
    public List<ScoreSheet> getScoreSheetByPlayerId(int playerId);

}
