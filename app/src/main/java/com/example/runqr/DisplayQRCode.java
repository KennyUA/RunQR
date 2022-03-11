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

        if (codeToDisplay.getLocation() != null){}

    }
}