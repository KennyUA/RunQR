package com.example.runqr;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button AddQR = findViewById(R.id.add_qr_button);
        AddQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addQRIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(addQRIntent);
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                ArrayList<String> QRCodeList = new ArrayList<String>();
                QRCodeList.add(IntentIntegrator.QR_CODE);
                intentIntegrator.setDesiredBarcodeFormats(QRCodeList);
                intentIntegrator.initiateScan();



            }



        });





    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(scanResult != null){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            alertDialog.setMessage("Would you like to add this QR code?");
            alertDialog.setPositiveButton("yes", )


        }


    }

}