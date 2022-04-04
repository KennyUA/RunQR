package com.example.runqr;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    //impliment camera class from android studios "https://developer.android.com/guide/topics/media/camera"

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    PreviewView previewView;
    Button takePicture;
    private ImageCapture imageCapture;
    Bitmap bitmap;
    Photo photo;
    //Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        takePicture = findViewById(R.id.takePhoto);

        previewView = findViewById(R.id.previewView);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, getExecutor());
    }

    private Executor getExecutor() {
        return ContextCompat.getMainExecutor(this);
    }

    private void startCameraX(ProcessCameraProvider cameraProvider) {
        //Structure of the code referenced from "https://www.youtube.com/watch?v=IrwhjDtpIU0" by Coding Reel
        cameraProvider.unbindAll();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        Preview preview = new Preview.Builder().build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.takePhoto) {
            //capturePhoto();
            //recieved from: https://developer.android.com/reference/androidx/camera/view/PreviewView#public-methods_1
            photo = new Photo();

            bitmap = previewView.getBitmap();
            int byteCount = bitmap.getByteCount();
            if(byteCount > 8000000){
                bitmap = bitmap.createScaledBitmap(bitmap,100,100,false);
            }
            //photo.setImage(bitmap);
            /*Context context = mContext;
            CharSequence text = "This Picture Has Been Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();*/
            photo.setImage(bitmap);
            //Intent intent = new Intent(this, AddQRFragment.class);
            //intent.putExtra("PhotoImage", (Parcelable) photo);
            Intent intent = new Intent(this, NewActivity.class);
            intent.putExtra("BitmapImage", bitmap);
            finish();

        }
    }

    private void capturePhoto() {

        //File photoDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/CameraXPhotos");
        //if(!photoDir.exists())
        //photoDir.mkdir();



        //Date date = new Date();
        //String timestamp = String.valueOf(date.getTime());
        long timestamp = System.currentTimeMillis();
        //String photoFilePath = photoDir.getAbsolutePath() + "/" + timestamp + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

        //File photoFile = new File(photoFilePath);

        //File photoFile;
        //photoFile = new File(Environment.getExternalStorageDirectory(),
        //"IMG_${System.currentTimeMillis()}.jpg");

        // Create output options object which contains file + metadata
        //ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();

        imageCapture.takePicture(

                new ImageCapture.OutputFileOptions.Builder(
                        getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                ).build(),
                getExecutor(),
                new ImageCapture.OnImageSavedCallback(){

                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        //ImageDecoder.Source source = ImageDecoder.createSource(contentValues, outputFileResults.getSavedUri());
                        Context context = null;
                        try {
                            context.getContentResolver().openInputStream(outputFileResults.getSavedUri());

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Log.v("hello","Photo saved");
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Log.v("hello","Photo not saved");

                    }
                }

        );

    }

}