package com.campbell.gamescorer;

import java.util.Comparator;

/**
 * Created by Campbell on 27/04/2015.
 */

public class PlayerScore {

    private int id = -1;
    private String name;
    private long score;
    private int playerNumber;
    private long lastUpdate;

    public PlayerScore() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public String toString() {
        return "PlayerScore [id=" + id + ", name=" + name + ", playerNumber=" + playerNumber
                + ", score=" + score + "]";
    }

    public static Comparator<PlayerScore> sortByPlayerNumber() {
        return new Comparator<PlayerScore>() {

            @Override
            public int compare(PlayerScore left, PlayerScore right) {
                return left.getPlayerNumber() - right.getPlayerNumber();
            }
        };
    }

    public static Comparator<PlayerScore> sortByScore() {
        return new Comparator<PlayerScore>() {

            @Override
            public int compare(PlayerScore lhs, PlayerScore rhs) {
                return Long.valueOf(lhs.getScore()).compareTo(rhs.getScore());
            }
        };
    }

}

