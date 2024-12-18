package com.game.scoresheet.model;

import java.util.HashMap;
import java.util.Map;

public class ScoreSheetForTeams {
        private int gameNumber;
        private Map<Player,Team> teamEntries=new HashMap<>();
        private Map<Integer,Team> finalTeamEntries=new HashMap<>();

        public int getGameNumber() {
                return gameNumber;
        }

        public void setGameNumber(int gameNumber) {
                this.gameNumber = gameNumber;
        }

        public Map<Player, Team> getTeamEntries() {
                return teamEntries;
        }

        public void setTeamEntries(Map<Player, Team> teamEntries) {
                this.teamEntries = teamEntries;
        }

        public Map<Integer, Team> getFinalTeamEntries() {
                return finalTeamEntries;
        }

        public void setFinalTeamEntries(Map<Integer, Team> finalTeamEntries) {
                this.finalTeamEntries = finalTeamEntries;
        }
}
