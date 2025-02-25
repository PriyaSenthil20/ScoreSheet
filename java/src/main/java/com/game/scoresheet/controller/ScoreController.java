package com.game.scoresheet.controller;

import com.game.scoresheet.dao.ScoreSheetDao;
import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.ScoreSheet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/games/scores")
public class ScoreController {
    private final ScoreSheetDao sheetDao;

    public ScoreController(ScoreSheetDao scoreSheetForPlayer) {
        this.sheetDao =scoreSheetForPlayer;
    }
    @GetMapping
    public List<ScoreSheet> getScores(){
        try{
            return sheetDao.getScoreSheets();
        }
        catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/round/{roundNumber}")
    public List<ScoreSheet> getScoresByRoundNumber(@PathVariable int roundNumber) {
        try {
            return sheetDao.getScoreSheetByRoundNumber(roundNumber);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/player/{playerId}")
    public List<ScoreSheet> getScoresByPlayerId(@PathVariable int playerId) {
        try {
            return sheetDao.getScoreSheetByPlayerId(playerId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ScoreSheet createScore( @RequestBody ScoreSheet scoreSheet) {
        try {
            return sheetDao.createScoreSheet(scoreSheet);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create Game.");
        }
    }

}
