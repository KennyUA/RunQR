package com.example.runqr;

import java.io.Serializable;
import java.util.ArrayList;

public class Account  implements Serializable {

    private String username;
    private String contactEmail;


    public Account() {
    }

    public Account(String username, String contactEmail) {
        this.username = username;
        this.contactEmail = contactEmail;
    }

    //public ArrayList<>



    public String getUsername(){
        return this.username;
    }






}
