package com.example.runqr;

import java.io.Serializable;
import java.util.ArrayList;

public class Account  implements Serializable {

    private String username;
    private PlayerStats playerStats;
    private String contactEmail;

    private ArrayList<QRCode> QRLibrary;

    //public ArrayList<>

    public void Account(){
        this.playerStats = new PlayerStats();
    }


    public void addQRCode(QRCode newQRCode){
        QRLibrary.add(newQRCode);
    }




}
