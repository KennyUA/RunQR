package com.example.runqr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerStatsTest {

    PlayerStats playerStats = new PlayerStats("abcde",null,null,0,0,0,0,0);
    

    @Test
    public void beginningStatsCorrect() {
        assertEquals(0, playerStats.getNumOfScanned());
        assertEquals(0, playerStats.getSumOfScores());
        assertEquals(0, playerStats.getHighQr().getScore());
        assertEquals(0, playerStats.getLowQr().getScore());
    }


    @Test
    public void testNumScanned() {
        int numScanned = playerStats.getNumOfScanned();
        assertEquals(0, numScanned);
        playerStats.setNumOfScanned(numScanned+1);
        assertEquals(1, playerStats.getNumOfScanned());
        playerStats.setNumOfScanned(10);
        assertEquals(10, playerStats.getNumOfScanned());
        playerStats.setNumOfScanned(1);

    }

    @Test
    public void testSumScores(){
        int newScore = 99;
        int sumSoFar = playerStats.getSumOfScores();
        assertEquals(0, playerStats.getSumOfScores());
        playerStats.setSumOfScores(sumSoFar+newScore);
        assertEquals(99, playerStats.getSumOfScores());
        int scoreToDelete = 89;
        sumSoFar = playerStats.getSumOfScores();
        playerStats.setSumOfScores(sumSoFar - scoreToDelete);
        assertEquals(10, playerStats.getSumOfScores());
    }



    @Test
    public void testHighAndLowQr() {

        assertEquals(0, playerStats.getHighQr().getScore());
        assertEquals(0, playerStats.getLowQr().getScore());
        /*this QRCode has a score of 11*/
        QRCode QR11 = new QRCode("185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969");
        playerStats.setLowQr(QR11);

        /*this QRCode has a score of 15*/
        QRCode QR15 = new QRCode("aba879191cf3e7c608eba98617c6d90d95bd092c70c93c0fa20c00637f566ff5");

        playerStats.setHighQr(QR15);

        QRCode QR14 = new QRCode("2ca0a77816f6dce72e5c147cc2225cf1392362abaff9d70c7d40de1298de9006");
        QRCode QR13 = new QRCode("7545cebf8ed70eaa018f289e657abfab325dbc54ebb00e6c278c32f600c987fa");
        QRCode QR18 = new QRCode("d38dad5db515ab4aa6caeb0b81d6fb775dc3c19bf1631ebdc8eee5bdc59cff59");
        QRCode QR10 = new QRCode("2f0eb1859e295bcd183127558f3c205270e7a8004ad362e5123bd5b2774e0f9c");

        assert(QR14.getScore() > playerStats.getLowQr().getScore());
        assert(QR13.getScore() > playerStats.getLowQr().getScore());
        assert(QR18.getScore() > playerStats.getLowQr().getScore());
        assert(QR10.getScore() < playerStats.getLowQr().getScore());
        playerStats.setLowQr(QR10);


        assert(QR14.getScore() < playerStats.getHighQr().getScore());
        assert(QR13.getScore() < playerStats.getHighQr().getScore());
        assert(QR18.getScore() > playerStats.getHighQr().getScore());
        playerStats.setHighQr(QR18);
        assert(QR10.getScore() < playerStats.getHighQr().getScore());

        assertEquals(10, playerStats.getLowQr().getScore());
        assertEquals(18, playerStats.getHighQr().getScore());

    }



}
