package com.example.runqr;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayQRCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_qrcode);

        QRCode codeToDisplay = (QRCode) getIntent().getSerializableExtra("QRCode to display");

        ImageView QRCodePhoto = findViewById(R.id.photoInfo);
        TextView QRCodeStats = findViewById(R.id.statInfo);
        TextView QRCodeLocation = findViewById(R.id.locationInfo);

        QRCodeLocation.setText("Location: \n");
        if (codeToDisplay.getLocation() != null){
            QRCodeLocation.append("X "+codeToDisplay.getLocation().getX()+"\n"+"Y "+codeToDisplay.getLocation().getY());

        }
        if (codeToDisplay.getPhoto() != null){
            //QRCodePhoto.setImageBitmap(codeToDisplay.getPhoto());

        }
        QRCodeStats.setText("Score: "+codeToDisplay.getScore());

    }
}