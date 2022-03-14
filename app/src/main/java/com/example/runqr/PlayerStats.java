package com.example.runqr;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerStats implements Serializable {

    public String username;

    /*stats*/
    public int highQr;
    public int lowQr;
    public int sumOfScores;
    public int numOfScanned;

    /* in activity, string of rank will be displayed according to int stored in PlayerStats object*/
    /*according to range, will be Platinum, Gold, etc*/
    public int rankNumOfScanned;
    public int rankHighQr;
    public int rankSumOfScores;


    public PlayerStats(String username, int highQr, int lowQr, int sumScores, int numScanned, int rankHighQr, int rankNumScanned, int rankSumScores) {
        highQr = highQr;
        lowQr = lowQr;
        sumOfScores = sumScores;
        numOfScanned = numScanned;
        rankHighQr = rankHighQr;
        rankNumOfScanned = rankNumScanned;
        rankSumOfScores = rankSumScores;
        this.username = username;

    }

    public PlayerStats(String username) {
        this.username = username;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("Accounts").document(username);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        rankHighQr = Integer.parseInt((String) document.get("highScoreRanking"));
                        rankNumOfScanned = Integer.parseInt((String) document.get("numberOfScannedRanking"));
                        rankSumOfScores = Integer.parseInt((String) document.get("totalScoreRanking"));
                        numOfScanned = Integer.parseInt((String) document.get("numberOfScanned"));
                        sumOfScores = Integer.parseInt((String) document.get("totalScore"));
                        String highQrString = (String) document.get("highScore");
                        String lowQrString = (String) document.get("lowScore");

                    }
                }
            }
        });
    }


    public void updatePlayerStats(String field, String value) {
        final String TAG = "Error Message: ";
        HashMap<String, String> data = new HashMap<>();
        data.put(field, value);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Accounts").document(this.username);
        docRef.update(field, value)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });;
    }
/*
    public void setPlayerStats(String field, String value) {
        final String TAG = "Error Message: ";
        HashMap<String, String> data = new HashMap<>();
        data.put(field, value);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Accounts").document(this.username);
        data.put(field, value);
        docRef.set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });;
    }
*/


    public int getHighQr() {
        return highQr;
    }

    public void setHighQr(int highQr) {
        this.highQr = highQr;
        updatePlayerStats("playerInfo.playerStats.high_qr", String.valueOf(highQr));
    }

    public int getLowQr() {
        return lowQr;
    }

    public void setLowQr(int lowQr) {
        this.lowQr = lowQr;
        updatePlayerStats("playerInfo.playerStats.low_qr", String.valueOf(lowQr));
    }

    public int getSumOfScores() {
        return sumOfScores;
    }

    public void setSumOfScores(int sumOfScores) {
        this.sumOfScores = sumOfScores;
        updatePlayerStats("playerInfo.playerStats.sum_of_scores", String.valueOf(sumOfScores));
    }

    public int getNumOfScanned() {
        return numOfScanned;
    }

    public void setNumOfScanned(int numOfScanned) {
        this.numOfScanned = numOfScanned;
        updatePlayerStats("playerInfo.playerStats.num_of_scanned", String.valueOf(numOfScanned));
    }

    public int getRankNumOfScanned() {
        return rankNumOfScanned;
    }

    public void setRankNumOfScanned(int rankNumOfScanned) {
        this.rankNumOfScanned = rankNumOfScanned;
        updatePlayerStats("playerInfo.playerStats.rank_num_of_scanned", String.valueOf(numOfScanned));
    }

    public int getRankHighQr() {
        return rankHighQr;
    }

    public void setRankHighQr(int rankHighQr) {
        this.rankHighQr = rankHighQr;
        updatePlayerStats("playerInfo.playerStats.rank_high_qr", String.valueOf(rankHighQr));
    }

    public int getRankSumOfScores() {
        return rankSumOfScores;
    }

    public void setRankSumOfScores(int rankSumOfScores) {
        this.rankSumOfScores = rankSumOfScores;
        updatePlayerStats("playerInfo.playerStats.rank_sum_of_scores", String.valueOf(rankSumOfScores));
    }
}
