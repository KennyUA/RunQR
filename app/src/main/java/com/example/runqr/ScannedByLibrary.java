package com.example.runqr;

import java.util.ArrayList;

public class ScannedByLibrary {
    private ArrayList<Player> scannedByList;

    public ScannedByLibrary(ArrayList<Player> scannedByList){
        this.scannedByList = scannedByList;
    }

    public ArrayList<Player> getScannedByList() {
        return this.scannedByList;
    }
}
