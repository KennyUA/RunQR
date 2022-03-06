package com.example.runqr;

import java.io.Serializable;
import java.util.ArrayList;

public class Account  implements Serializable {

    private String username;
    private PlayerStats playerStats;
    private String contactEmail;

    private ArrayList<QRCode> QRLibrary;

<<<<<<< Updated upstream
=======
    //public ArrayList<>

>>>>>>> Stashed changes
    public Account(String username, PlayerStats playerStats, String contactEmail) {
        this.username = username;
        this.playerStats = playerStats;
        this.contactEmail = contactEmail;
<<<<<<< Updated upstream
=======
    }

    public void Account(){

        this.playerStats = new PlayerStats();
        this.username = "test_username";
>>>>>>> Stashed changes
    }

    public String getUsername(){
        return this.username;
    }

    public void addQRCode(QRCode newQRCode){
        QRLibrary.add(newQRCode);
    }




}
