package com.example.runqr;

import java.io.Serializable;
import java.util.ArrayList;

public class QRLibrary implements Serializable {

    private ArrayList<QRCode> QRLibrary;
    private int QRLibraryScore;

    public void QRLibrary(){
        this.QRLibrary = new ArrayList<>();
        this.QRLibraryScore = 0;
    }

    public void addQRCode(QRCode newQRCode){

        QRLibrary.add(newQRCode);
        QRLibraryScore += newQRCode.getScore();
    }

    public ArrayList<QRCode> getQRLibrary(){
        return this.QRLibrary;
    }

    public int getQRLibraryScore() {
        return this.QRLibraryScore;
    }
    public void deleteQRCode(QRCode removeQRCode) {
        QRLibrary.remove(removeQRCode);
        QRLibraryScore -= removeQRCode.getScore();
    }

    public QRCode getQRCode(int position) {
        return QRLibrary.get(position);
    }

}
