package com.game.scoresheet.controller;

import com.game.scoresheet.dao.PlayerDao;
import com.game.scoresheet.exception.DaoException;
import com.game.scoresheet.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/games/players")
public class PlayerController {

    private final PlayerDao playerDao;

    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping
    public List<Player> getListOfGames() {
        try {
            return playerDao.getPlayers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{playerId}")
    public Player getPlayerById(@PathVariable int playerId) {
        try {
            return playerDao.getPlayerById(playerId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        try {
            return playerDao.createPlayer(player);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create Player.");
        }
    }
    @PutMapping
    public boolean deletePlayer(@RequestBody Player player){
        try {
            return  playerDao.deletePlayer(player);
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete Player.");
        }
    }

}

