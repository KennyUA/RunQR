package com.example.runqr;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// This class is the activity that displays the details of QRCodes and allows player to browse their QRCodes.
// Once an item, corresponding to a QRCode, in ListView of QRLibraryActivity is clicked the DisplayQRActivity is opened to display QRCode details.
// QRCode details being displayed so far are: score, location (X and Y coordinates) if it exists, and image if it exists.


public class DisplayQRCodeActivity extends AppCompatActivity {

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
            QRCodeLocation.append("X: "+codeToDisplay.getLocation().getX()+"\n"+"Y: "+codeToDisplay.getLocation().getY());

        }
        if (codeToDisplay.getPhoto() != null){
            //QRCodePhoto.setImageBitmap(codeToDisplay.getPhoto());

        }
        QRCodeStats.setText("Score: "+codeToDisplay.getScore());

    }
}