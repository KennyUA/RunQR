package com.example.runqr;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Scanner {
    // scans QRCode and hashes contents and creates new QRCode instance with resulting hash stored
    private String hash;


    public void addQRCode(String hashedContent){

        // create new QRCode object
        QRCode qRCode = new QRCode(hashedContent);


    }

    public String hashQRCode(String rawContent){

        // do hashing here
        String sha256hex = Hashing.sha256()
                .hashString(rawContent, StandardCharsets.UTF_8)
                .toString();
        //this.hash = sha256hex;
        return sha256hex;
    }



}
