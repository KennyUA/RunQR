package com.example.runqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    ListView profileList;
    ArrayAdapter<ProfileItem> profileAdapter;
    ArrayList<ProfileItem> profileDataList;
    String[] items;
    String[] values;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // To retrieve object in second Activity
        Player player = (Player) getIntent().getSerializableExtra("Player");
        PlayerStats playerStats = player.getPlayerStats();



        profileList = findViewById(R.id.profile_list);

        String[] items = {"Scanned QR Codes: ", "Total Score: ", "Rank (number of codes): ", "Rank (player): ", "Rank (highest scoring code): ", "Highest Scoring: ", "Lowest Scoring: "};
        String[] values = {String.valueOf(playerStats.getNumOfScanned()), String.valueOf(playerStats.getSumOfScores()), String.valueOf(playerStats.getRankNumOfScanned()), String.valueOf(playerStats.getRankSumOfScores()), String.valueOf(playerStats.getRankHighQr()), String.valueOf(playerStats.getHighQr()), String.valueOf(playerStats.getLowQr())};

        profileDataList = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            profileDataList.add((new ProfileItem(items[i], values[i])));
        }

        profileAdapter = new ProfileCustomList(this, profileDataList);
        profileList.setAdapter(profileAdapter);


        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }

        });

    }



}