package com.example.runqr;

import com.google.common.hash.Hashing;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * This class represents the scanner used by player to scan QRCode objects in the RunQR game.
 * This class is responsible for both scanning the QRCode and hashing its string contents so that contents are not stored by the game (for privacy concerns).
 * Once hashed, Scanner creates a new QRCode object with the resulting hash.
 * Scanner is used so that the String contents of QRCode are not stored or known by the QRCode object.
 */
public class Scanner implements Serializable {

    private String hash;


    public String hashQRCode(String rawContent){

        String sha256hex = Hashing.sha256()
                .hashString(rawContent, StandardCharsets.UTF_8)
                .toString();
        //this.hash = sha256hex;
        return sha256hex;
    }



}
