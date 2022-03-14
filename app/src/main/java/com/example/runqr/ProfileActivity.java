package com.example.runqr;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This class represents a ProfileActivity object in the RunQR game.
 * This class displays all Player Stats such as high score QR, number of scanned codes, etc
 *
 */

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
                ProfileActivity.super.onBackPressed();
                //finish();
            }

        });




    }



}