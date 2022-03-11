package com.example.runqr;

import java.io.Serializable;

public class PlayerStats implements Serializable {
    //private Player player;

    /*stats*/
    private int high_qr = 0;
    private int low_qr = 0;
    private int sum_of_scores = 0;
    private int num_of_scanned = 0;

    /* in activity, string of rank will be displayed according to int stored in PlayerStats object*/
    /*according to range, will be Platinum, Gold, etc*/
    private int rank_num_of_scanned = 0;
    private int rank_high_qr = 0;
    private int rank_sum_of_scores = 0;

    /*
    public PlayerStats(Player player) {
        this.player = player;
    }
     */

    public int getHigh_qr() {
        return high_qr;
    }

    public void setHigh_qr(int high_qr) {
        this.high_qr = high_qr;
    }

    public int getLow_qr() {
        return low_qr;
    }

    public void setLow_qr(int low_qr) {
        this.low_qr = low_qr;
    }

    public int getSum_of_scores() {
        return sum_of_scores;
    }

    public void setSum_of_scores(int sum_of_scores) {
        this.sum_of_scores = sum_of_scores;
    }

    public int getNum_of_scanned() {
        return num_of_scanned;
    }

    public void setNum_of_scanned(int num_of_scanned) {
        this.num_of_scanned = num_of_scanned;
    }

    public int getRank_num_of_scanned() {
        return rank_num_of_scanned;
    }

    public void setRank_num_of_scanned(int rank_num_of_scanned) {
        this.rank_num_of_scanned = rank_num_of_scanned;
    }

    public int getRank_high_qr() {
        return rank_high_qr;
    }

    public void setRank_high_qr(int rank_high_qr) {
        this.rank_high_qr = rank_high_qr;
    }

    public int getRank_sum_of_scores() {
        return rank_sum_of_scores;
    }

    public void setRank_sum_of_scores(int rank_sum_of_scores) {
        this.rank_sum_of_scores = rank_sum_of_scores;
    }
}
