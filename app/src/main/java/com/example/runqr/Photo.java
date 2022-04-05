package com.example.runqr;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Photo implements Serializable {
    private Bitmap image;
    private Photo photo;
    /*
    public Photo(){
        this.photo = photo;
    }

     */

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
