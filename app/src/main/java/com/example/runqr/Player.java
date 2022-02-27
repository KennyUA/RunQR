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

    public void Player(){
        this.playerAccount = new Account();
    }


    public void addQRCode(QRCode QRCodeToAdd){
        playerAccount.addQRCode(QRCodeToAdd);
    }



    public void scanQR(){

    }







}
