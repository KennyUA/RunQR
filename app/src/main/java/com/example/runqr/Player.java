package com.example.runqr;

import java.io.Serializable;

/**
 * Represents a Player object
 *
 *
 *
 */
public class Player implements Serializable {
    private Account playerAccount;
    private PlayerStats playerStats;
    private QRLibrary playerQRLibrary;
    //private ArrayList<QRCode> playerQRLibrary;

    public void Player(){

        this.playerAccount = new Account();
        this.playerStats = new PlayerStats();
        this.playerQRLibrary = new QRLibrary();
    }


    public void addQRCode(QRCode QRCodeToAdd){
        this.playerQRLibrary.addQRCode(QRCodeToAdd);
    }

    public void deleteQRCode(QRCode QRCodeToDelete) {
        this.playerQRLibrary.deleteQRCode(QRCodeToDelete);
    }
    public Account getPlayerAccount(){
        return this.playerAccount;
    }

    public PlayerStats getPlayerStats() {
        return this.playerStats;
    }

    public QRLibrary getPlayerQRLibrary() {
        return this.playerQRLibrary;
    }



    public void scanQR(){

    }







}
