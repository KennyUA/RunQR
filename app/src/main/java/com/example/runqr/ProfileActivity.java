package com.example.runqr;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * This class represents a ProfileActivity object in the RunQR game.
 * This class displays all Player Stats such as high score QR, number of scanned codes, etc
 *
 */

public class ProfileActivity extends AppCompatActivity {

    ListView profileList;
    ExtendedFloatingActionButton viewQRCodesButton;
    ArrayAdapter<ProfileItem> profileAdapter;
    ArrayList<ProfileItem> profileDataList;
    String[] items;
    String[] values;
    private String rankNumOfScannedString;
    private String rankHighQrString;
    private String rankSumOfScoresString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // To retrieve object in second Activity
        Player player = (Player) getIntent().getSerializableExtra("Display Player Profile");
        PlayerStats playerStats = player.getPlayerStats();



        profileList = findViewById(R.id.profile_list);
        viewQRCodesButton = findViewById(R.id.view_qrcodes_button);

        String highQrString = "N/A";
        String lowQrString = "N/A";
        //String rankHighQrString = "N/A";
        //String rankNumOfScannedString = "N/A";
        //String rankSumOfScoresString = "N/A";


        if (playerStats.getHighQr() != null) {
            highQrString = String.valueOf(playerStats.getHighQr().getScore());
        }

        if (playerStats.getLowQr() != null) {
            lowQrString = String.valueOf(playerStats.getLowQr().getScore());
        }


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Accounts").document(playerStats.username).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "entered firebase profile activity");
                        String rankHighQrString = (String) document.get("playerStats.rankHighQr");
                        String rankNumOfScannedString = (String) document.get("playerStats.rankNumOfScanned");
                        String rankSumOfScoresString = (String) document.get("playerStats.rankSumOfScores");
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });



        String[] items = {"Scanned QR Codes: ", "Total Score: ", "Rank (number of codes): ", "Rank (player): ", "Rank (highest scoring code): ", "Highest Scoring: ", "Lowest Scoring: "};
        String[] values = {String.valueOf(playerStats.getNumOfScanned()), String.valueOf(playerStats.getSumOfScores()), rankNumOfScannedString, rankSumOfScoresString, rankHighQrString, highQrString, lowQrString};

        profileDataList = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            profileDataList.add((new ProfileItem(items[i], values[i])));
        }

        profileAdapter = new ProfileCustomList(this, profileDataList);
        profileList.setAdapter(profileAdapter);


        viewQRCodesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open the player's qr library
                Intent intent = new Intent(ProfileActivity.this, QRLibraryActivity.class);
                intent.putExtra("Player QRLibraryActivity", player);
                intent.putExtra("Allow Deletion?", false);
                startActivity(intent);
            }
        });

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ProfileActivity.super.onBackPressed();
                //finish();
            }

        });




    }



}
