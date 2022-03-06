package com.example.runqr;

import java.io.Serializable;

/**
 * Represents a Player object
 *
 *
 *
 */
public class Player  implements Serializable {
    private Account playerAccount;

<<<<<<< Updated upstream
    public Player(Account playerAccount) {
        this.playerAccount = playerAccount;
    }

=======

    public Player(Account playerAccount) {
        this.playerAccount = playerAccount;
    }

>>>>>>> Stashed changes
    public void addQRCode(QRCode QRCodeToAdd){
        playerAccount.addQRCode(QRCodeToAdd);
    }

    public Account getPlayerAccount(){
        return this.playerAccount;
    }


    public void scanQR(){

    }







}
