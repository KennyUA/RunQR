package com.example.runqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
        String[] values = {String.valueOf(playerStats.getNum_of_scanned()), String.valueOf(playerStats.getSum_of_scores()), String.valueOf(playerStats.getRank_num_of_scanned()), String.valueOf(playerStats.getRank_sum_of_scores()), String.valueOf(playerStats.getRank_high_qr()), String.valueOf(playerStats.getHigh_qr()), String.valueOf(playerStats.getLow_qr())};

        profileDataList = new ArrayList<>();
        for (int i = 0; i<items.length;i++){
            profileDataList.add((new ProfileItem(items[i], values[i])));
        }

        profileAdapter = new ProfileCustomList(this, profileDataList);
        profileList.setAdapter(profileAdapter);
    }


}