package com.example.runqr;

import java.util.ArrayList;

public class Map {
    private ArrayList<QRCode> qrCodes = new ArrayList<QRCode>();
    private Location playerLocation;
    private double distanceDisplay;

    public Map(ArrayList<QRCode> qrCodes, Location playerLocation, int distanceDisplay) {
        this.qrCodes = qrCodes;
        this.playerLocation = playerLocation;
        this.distanceDisplay = distanceDisplay;
    }

    public void displayOnMap(){
        for(int i=0;i< qrCodes.size();i++){
            QRCode qrCode = qrCodes.get(i);
            int xCor = qrCode.getLocation().getX();
            int yCor = qrCode.getLocation().getY();
            if(Math.sqrt(Math.pow(xCor - playerLocation.getX(),2) + Math.pow(yCor - playerLocation.getY(),2)) <= distanceDisplay){

            }

        }
    }
}
