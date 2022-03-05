package com.example.runqr;

import java.io.Serializable;
import java.util.ArrayList;

public class Account  implements Serializable {

    private String username;
    private PlayerStats playerStats;
    private String contactEmail;

    private ArrayList<QRCode> QRLibrary;

    public Account(String username, PlayerStats playerStats, String contactEmail) {
        this.username = username;
        this.playerStats = playerStats;
        this.contactEmail = contactEmail;
    }

    public String getUsername(){
        return this.username;
    }

    public void addQRCode(QRCode newQRCode){
        QRLibrary.add(newQRCode);
    }




}
