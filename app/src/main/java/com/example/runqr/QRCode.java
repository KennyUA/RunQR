package com.example.runqr;

public class QRCode {

    private int score;

    private String hash;

    private Location location;

    public QRCode(int score, String hash, Location location) {
        this.score = score;
        this.hash = hash;
        this.location = location;
    }

    public int getScore() {
        return score;
    }

    public String getHash() {
        return hash;
    }

    public Location getLocation() {
        return location;
    }
}
