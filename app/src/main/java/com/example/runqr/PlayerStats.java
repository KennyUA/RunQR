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
    public int high_qr;
    public int low_qr;
    public int sum_of_scores;
    public int num_of_scanned;

    /* in activity, string of rank will be displayed according to int stored in PlayerStats object*/
    /*according to range, will be Platinum, Gold, etc*/
    public int rank_num_of_scanned;
    public int rank_high_qr;
    public int rank_sum_of_scores;


    //public PlayerStats(QRCode highQR, QRCode lowQR, Integer sumScores, Integer numScanned, Integer rankHighQR,
                       //Integer rankNumScanned, Integer rankSumScores) {
    public PlayerStats(String username, int highQr, int lowQr, int sumScores, int numScanned, int rankHighQr, int rankNumScanned, int rankSumScores) {
        high_qr = highQr;
        low_qr = lowQr;
        sum_of_scores = sumScores;
        num_of_scanned = numScanned;
        rank_high_qr = rankHighQr;
        rank_num_of_scanned = rankNumScanned;
        rank_sum_of_scores = rankSumScores;
        this.username = username;
        //updatePlayerStats("playerInfo.playerStats.high_qr", "3");

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
                        rank_high_qr = Integer.parseInt((String) document.get("high_score_ranking"));
                        rank_num_of_scanned = Integer.parseInt((String) document.get("number_of_scanned_ranking"));
                        rank_sum_of_scores = Integer.parseInt((String) document.get("total_score_ranking"));
                        num_of_scanned = Integer.parseInt((String) document.get("number_of_scanned"));
                        sum_of_scores = Integer.parseInt((String) document.get("total_score"));
                        String high_qr_string = (String) document.get("high_score");
                        String low_qr_string = (String) document.get("low_score");

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



    public int getHigh_qr() {
        return high_qr;
    }

    public void setHigh_qr(int high_qr) {
        this.high_qr = high_qr;
        updatePlayerStats("playerInfo.playerStats.high_qr", String.valueOf(high_qr));
    }

    public int getLow_qr() {
        return low_qr;
    }

    public void setLow_qr(int low_qr) {
        this.low_qr = low_qr;
        updatePlayerStats("playerInfo.playerStats.low_qr", String.valueOf(low_qr));
    }

    public int getSum_of_scores() {
        return sum_of_scores;
    }

    public void setSum_of_scores(int sum_of_scores) {
        this.sum_of_scores = sum_of_scores;
        updatePlayerStats("playerInfo.playerStats.sum_of_scores", String.valueOf(sum_of_scores));
    }

    public int getNum_of_scanned() {
        return num_of_scanned;
    }

    public void setNum_of_scanned(int num_of_scanned) {
        this.num_of_scanned = num_of_scanned;
        updatePlayerStats("playerInfo.playerStats.num_of_scanned", String.valueOf(num_of_scanned));
    }

    public int getRank_num_of_scanned() {
        return rank_num_of_scanned;
    }

    public void setRank_num_of_scanned(int rank_num_of_scanned) {
        this.rank_num_of_scanned = rank_num_of_scanned;
        updatePlayerStats("playerInfo.playerStats.rank_num_of_scanned", String.valueOf(num_of_scanned));
    }

    public int getRank_high_qr() {
        return rank_high_qr;
    }

    public void setRank_high_qr(int rank_high_qr) {
        this.rank_high_qr = rank_high_qr;
        updatePlayerStats("playerInfo.playerStats.rank_high_qr", String.valueOf(rank_high_qr));
    }

    public int getRank_sum_of_scores() {
        return rank_sum_of_scores;
    }

    public void setRank_sum_of_scores(int rank_sum_of_scores) {
        this.rank_sum_of_scores = rank_sum_of_scores;
        updatePlayerStats("playerInfo.playerStats.rank_sum_of_scores", String.valueOf(rank_sum_of_scores));
    }
}
