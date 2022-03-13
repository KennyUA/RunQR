package com.example.runqr;

import com.google.common.hash.Hashing;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * This class represents the hasher which takes in contents of QRCode scanned in AddQRFragment and hashes them.
 * This class is responsible for hashing scanned QRCodes's string contents so that contents are not stored by the game (for privacy concerns).
 * Hasher is used as an intermediary so that contents of the QRCode are not stored or known by the QRCode object.
 */
public class Hasher implements Serializable {

    public String hashQRCode(String rawContent){

        String sha256hex = Hashing.sha256()
                .hashString(rawContent, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }



}
