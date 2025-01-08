package com.game.scoresheet.dao;

import com.game.scoresheet.model.Rounds;

import java.util.List;

public interface RoundDao {
    public List<Rounds> getRounds();
    public Rounds getRoundById(int roundId);
    public Rounds getRoundByNumber(int roundNumber);
    public Rounds createRound(Rounds newRound);

}
