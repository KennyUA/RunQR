package com.example.runqr;

import java.io.Serializable;

public class PlayerStats implements Serializable {
    private int high_score;
    private int low_score;
    private int total_score;
    private int number_of_scanned;
    private int high_score_ranking;
    private int number_of_scanned_ranking;
    private int total_score_ranking;
    /*
    private Player player;


    public PlayerStats(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    */

    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }

    public int getLow_score() {
        return low_score;
    }

    public void setLow_score(int low_score) {
        this.low_score = low_score;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public int getNumber_of_scanned() {
        return number_of_scanned;
    }

    public void setNumber_of_scanned(int number_of_scanned) {
        this.number_of_scanned = number_of_scanned;
    }

    public int getHigh_score_ranking() {
        return high_score_ranking;
    }

    public void setHigh_score_ranking(int high_score_ranking) {
        this.high_score_ranking = high_score_ranking;
    }

    public int getNumber_of_scanned_ranking() {
        return number_of_scanned_ranking;
    }

    public void setNumber_of_scanned_ranking(int number_of_scanned_ranking) {
        this.number_of_scanned_ranking = number_of_scanned_ranking;
    }

    public int getTotal_score_ranking() {
        return total_score_ranking;
    }

    public void setTotal_score_ranking(int total_score_ranking) {
        this.total_score_ranking = total_score_ranking;
    }

    public void incrementTotalScore (int addScore){
        this.total_score += addScore;
    }
}
