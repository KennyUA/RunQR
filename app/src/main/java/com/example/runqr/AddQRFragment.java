package com.example.runqr;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import androidx.fragment.app.Fragment;


import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class AddQRFragment extends Fragment {

    private static final int RC_PERMISSION = 10;
    private CodeScanner mCodeScanner;
    private boolean mPermissionGranted;
    private String QRString = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.add_qr_fragment_layout, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);

        Button confirmAddQR = root.findViewById(R.id.confirm_addQR_button);
        Button cancelAddQR = root.findViewById(R.id.cancel_addQR_button);

        Scanner QRCodeScanner = new Scanner();
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                // result is string corresponding to contents of QRCode
                // hash the result string of the QR Code and store hash in the QRCode

                QRString = result.getText();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        confirmAddQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add QRCode
                String hashedString = QRCodeScanner.hashQRCode(QRString);
                QRCodeScanner.addQRCode(hashedString);

                // prompt user to enter geolocation and/or photo of object hosting the QRCode

                //ignore for now

            }
        });

        cancelAddQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel Scanner by returning to main activity
                getFragmentManager().beginTransaction().remove(AddQRFragment.this).commit();
                final Button addQRButton = getActivity().findViewById(R.id.add_qr_button);
                addQRButton.setVisibility(View.VISIBLE);


            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }


}
