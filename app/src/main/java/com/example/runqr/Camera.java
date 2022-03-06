package com.example.runqr;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;


public class Camera {
    //impliment camera class from android studios "https://developer.android.com/guide/topics/media/camera"
    private boolean checkCamera(Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else{
            return false;
        }
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            //c =
        }
        catch (Exception e){
        }
        return c;
    }
}
