package com.campbell.gamescorer;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Campbell on 9/05/2015.
 */
public class Game implements Parcelable {

    private int id = -1;
    private String name;
    private int numPlayers;
    private List<PlayerScore> playerScores;

    private String gameType;

    private String turnType;
    private String winType;

    private int winningScore;


    public Game() {
    }

    public Game(Parcel in) {
        id = in.readInt();
        name = in.readString();
        numPlayers = in.readInt();
        gameType = in.readString();
        turnType = in.readString();
        winType = in.readString();

        winningScore = in.readInt();

        playerScores = new ArrayList<PlayerScore>();
        while (true) {
            PlayerScore playerScore = in.readParcelable(PlayerScore.class.getClassLoader());
            if (playerScore == null) {
                break;
            }
            playerScores.add(playerScore);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(numPlayers);
        dest.writeString(gameType);
        dest.writeString(turnType);
        dest.writeString(winType);
        dest.writeInt(winningScore);
        if (playerScores != null) {
            for (PlayerScore playerScore : playerScores) {
                dest.writeParcelable(playerScore, 0);
            }
        }
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<PlayerScore> getPlayerScores() {
        return this.playerScores;
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

    public String getTurnType() {
        return this.turnType;
    }
    public void setTurnType(String turnType) {
        this.turnType = turnType;
    }

    public String getWinType() {
        return this.winType;
    }
    public void setWinType(String winType) {
        this.winType = winType;
    }
}
