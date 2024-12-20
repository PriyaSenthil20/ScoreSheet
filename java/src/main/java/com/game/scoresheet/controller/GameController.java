package com.game.scoresheet.controller;
import com.game.scoresheet.dao.GameDao;
import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/games")
public class GameController {
    private final GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }
    @GetMapping
    public List<Game> getListOfGames(){
        try{
            return gameDao.getGames();
        }
        catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        try {
            return gameDao.getGameById(gameId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Game createGame( @RequestBody Game game) {
        try {
            return gameDao.createGame(game);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create Game.");
        }
    }


    }
