package com.example.runqr;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.runqr.placeholder.LeaderboardItemComparator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    ListView scoreList;
    ArrayAdapter<LeaderboardItem> scoreAdapter;
    ArrayList<LeaderboardItem> scoreDataList;
    ArrayList<String> players;
    ArrayList<String> scores;
    //int mode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Player player = (Player) getIntent().getSerializableExtra("Player");
        PlayerStats playerStats = player.getPlayerStats();



        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LeaderboardActivity.super.onBackPressed();
                //finish();
            }

        });


        Button mostValuableButton = (Button) findViewById(R.id.button_most_valuable);
        mostValuableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                refreshListView("playerStats.highQr.score", playerStats);
            }
        });

        Button mostScannedButton = (Button) findViewById(R.id.button_most_scanned);
        mostScannedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                refreshListView("playerStats.numOfScanned", playerStats);
            }
        });

        Button highestSumButton = (Button) findViewById(R.id.button_sum);
        highestSumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                refreshListView("playerStats.sumOfScores", playerStats);
            }
        });

        /* default view is most valuable*/
        refreshListView("playerStats.highQr.score", playerStats);
    }

    public void refreshListView(String scoreString, PlayerStats playerStats) {

        players = new ArrayList<String>();
        scores = new ArrayList<String>();

        scoreList = findViewById(R.id.leaderboard_list);
        scoreDataList = new ArrayList<LeaderboardItem>();
        scoreAdapter = new LeaderboardCustomList(this, scoreDataList);
        scoreList.setAdapter(scoreAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String finalScoreString = scoreString;
        db.collection("Accounts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String thisPlayer = (String) document.get("playerAccount.username");
                        String thisScore = String.valueOf(document.get(finalScoreString));
                        if (thisScore != "null") {
                            //scoreDataList.add(new LeaderboardItem(thisPlayer, thisScore));
                            players.add(thisPlayer);
                            scores.add(thisScore);
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            //Log.d(TAG, "player is " + thisPlayer + " and score is " + thisScore);
                        }
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                for (int i = 0; i<scores.size();i++){
                    scoreDataList.add((new LeaderboardItem(players.get(i), scores.get(i))));
                    scoreAdapter.notifyDataSetChanged();
                }
                LeaderboardItemComparator leaderboardItemComparator = new LeaderboardItemComparator();
                Collections.sort(scoreDataList, leaderboardItemComparator);
                /*figuring out player rankings*/
                int size = scoreDataList.size();
                int numPlatinum = 0; //first x% should be platinum
                int numGold = size/25; //next 4%
                int numSilver = size/10; //next 10%
                int numBronze = size/5; //next 20%
                scoreDataList.add(numBronze, new LeaderboardItem("BRONZE", " "));
                scoreDataList.add(numSilver, new LeaderboardItem("SILVER", " "));
                scoreDataList.add(numGold, new LeaderboardItem("GOLD", " "));
                scoreDataList.add(numPlatinum, new LeaderboardItem("PLATINUM", " "));
                scoreAdapter.notifyDataSetChanged();

                /*update rankings in player individual profiles*/
                int i = 0;
                String rank = "PLATINUM";
                Log.d(TAG, "size in refreashplayerstats is "+scoreDataList.size());
                for (i = 0; i < scoreDataList.size(); i++) {
                    Log.d(TAG, "i is " + String.valueOf(i));
                    LeaderboardItem thisItem = scoreDataList.get(i);
                    if (thisItem.getPlayer() != null) {
                        if (thisItem.getPlayer().equals("GOLD")) {
                            rank = "GOLD";
                        }
                        if (thisItem.getPlayer().equals("SILVER")) {
                            rank = "SILVER";
                        }
                        if (thisItem.getPlayer().equals("BRONZE")) {
                            rank = "BRONZE";
                        }
                        if (!thisItem.getPlayer().equals("PLATINUM") && !thisItem.getPlayer().equals("GOLD") && !thisItem.getPlayer().equals("SILVER") && !thisItem.getPlayer().equals("BRONZE")) {
                            /*find player based on username and update ranking*/
                            Log.d(TAG, "reached for loop i = " + String.valueOf(i));
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            String username = thisItem.getPlayer();
                            String finalRank = rank;
                            String rankString = "";
                            if (scoreString.equals("playerStats.highQr.score")) {
                                rankString = "playerStats.rankHighQr";
                            }
                            if (scoreString.equals("playerStats.numOfScanned")) {
                                rankString = "playerStats.rankNumOfScanned";
                            }
                            if (scoreString.equals("playerStats.sumOfScores")) {
                                rankString = "playerStats.rankSumOfScores";
                            }
                            String finalRankString = rankString;
                            db.collection("Accounts").document(username).update(rankString, rank)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                            Log.d("TESTTEST", "rankString = " + finalRankString + " and rank = " + finalRank);
                                            if (playerStats.username.equals(username)) {
                                                if (finalRankString.equals("playerStats.rankHighQr")) {
                                                    playerStats.setRankHighQr(finalRank);
                                                } else if (finalRankString.equals("playerStats.rankNumOfScanned")) {
                                                    playerStats.setRankNumOfScanned(finalRank);
                                                } else if (finalRankString.equals("playerStats.rankSumOfScores")) {
                                                    playerStats.setRankSumOfScores(finalRank);
                                                }
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });

                        }
                    }
                }


            }
        });
    }


}
