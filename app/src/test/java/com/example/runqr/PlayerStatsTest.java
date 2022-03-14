package com.example.runqr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerStatsTest {

    PlayerStats playerStats = new PlayerStats();
    

    @Test
    public void beginning_stats_correct() {
        assertEquals(0, playerStats.getNum_of_scanned());
        assertEquals(0, playerStats.getSum_of_scores());
        assertEquals(0, playerStats.getHigh_qr());
        assertEquals(0, playerStats.getLow_qr());
    }


    @Test
    public void test_num_scanned() {
        int num_scanned = playerStats.getNum_of_scanned();
        assertEquals(0, num_scanned);
        playerStats.setNum_of_scanned(num_scanned+1);
        assertEquals(1, playerStats.getNum_of_scanned());
        playerStats.setNum_of_scanned(10);
        assertEquals(10, playerStats.getNum_of_scanned());
        playerStats.setNum_of_scanned(1);

    }

    @Test
    public void test_sum_scores(){
        int new_score = 99;
        int sum_so_far = playerStats.getSum_of_scores();
        assertEquals(0, playerStats.getSum_of_scores());
        playerStats.setSum_of_scores(sum_so_far+new_score);
        assertEquals(99, playerStats.getSum_of_scores());
        int score_to_delete = 89;
        sum_so_far = playerStats.getSum_of_scores();
        playerStats.setSum_of_scores(sum_so_far - score_to_delete);
        assertEquals(10, playerStats.getSum_of_scores());
    }

    @Test
    public void test_high_and_low_qr() {
        assertEquals(0, playerStats.getHigh_qr());
        assertEquals(0, playerStats.getLow_qr());
        playerStats.setLow_qr(3);
        playerStats.setHigh_qr(44);
        int scoreA = 10;
        int scoreB = 43;
        int scoreC = 45;
        int scoreD = 1;

        assert(scoreA > playerStats.getLow_qr());
        assert(scoreB > playerStats.getLow_qr());
        assert(scoreC > playerStats.getLow_qr());
        assert(scoreD < playerStats.getLow_qr());
        playerStats.setLow_qr(scoreD);


        assert(scoreA < playerStats.getHigh_qr());
        assert(scoreB < playerStats.getHigh_qr());
        assert(scoreC > playerStats.getHigh_qr());
        playerStats.setHigh_qr(scoreC);
        assert(scoreD < playerStats.getHigh_qr());

        assertEquals(scoreD, playerStats.getLow_qr());
        assertEquals(scoreC, playerStats.getHigh_qr());

    }



}
