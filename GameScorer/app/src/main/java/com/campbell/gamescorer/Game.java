package com.campbell.gamescorer;

import java.util.List;

/**
 * Created by Campbell on 9/05/2015.
 */
public class Game {

    private int id = -1;
    private String name;
    private List<PlayerScore> playerScores;

    private String gameType;

    private int winningScore;
    private String roundType;
    private String winType;


    public Game() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<PlayerScore> getPlayerScores() {
        return playerScores;
    }
    public void setPlayerScores(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
    }
    public String getGameType() {
        return this.gameType;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
    public int getWinningScore() {
        return this.winningScore;
    }
    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }
    public String getRoundType() {
        return this.roundType;
    }
    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }
    public String getWinType() {
        return this.winType;
    }
    public void setWinType(String winType) {
        this.winType = winType;
    }
}
