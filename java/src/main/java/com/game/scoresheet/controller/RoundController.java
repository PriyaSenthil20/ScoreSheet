package com.game.scoresheet.controller;

import com.game.scoresheet.dao.RoundDao;
import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.Rounds;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/games/rounds")
public class RoundController {
    private final RoundDao roundDao;

    public RoundController(RoundDao roundDao) {
        this.roundDao = roundDao;
    }
    @GetMapping
    public List<Rounds> getListOfRounds(){
        try{
            return roundDao.getRounds();
        }
        catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{roundNumber}")
    public Rounds getRoundByNumber(@PathVariable int roundNumber) {
        try {
            return roundDao.getRoundByNumber(roundNumber);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rounds createGame( @RequestBody Rounds round) {
        try {
            return roundDao.createRound(round);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create Game.");
        }
    }

}
