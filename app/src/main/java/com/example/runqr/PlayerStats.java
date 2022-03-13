package com.example.runqr;

import java.io.Serializable;

public class PlayerStats implements Serializable {

    /*stats*/
    private QRCode high_qr;
    private QRCode low_qr;
    private Integer sum_of_scores;
    private Integer num_of_scanned;

    /* in activity, string of rank will be displayed according to int stored in PlayerStats object*/
    /*according to range, will be Platinum, Gold, etc*/
    private Integer rank_num_of_scanned;
    private Integer rank_high_qr;
    private Integer rank_sum_of_scores;

    public PlayerStats() {
        high_qr = null;
        low_qr = null;
        sum_of_scores = 0;
        num_of_scanned = 0;
        rank_high_qr = null;
        rank_num_of_scanned = null;
        rank_sum_of_scores = null;
    }

    //public PlayerStats(QRCode highQR, QRCode lowQR, Integer sumScores, Integer numScanned, Integer rankHighQR,
                       //Integer rankNumScanned, Integer rankSumScores) {
    public PlayerStats(Integer sumScores, Integer numScanned) {
        //high_qr = highQR;
        //low_qr = lowQR;
        sum_of_scores = sumScores;
        num_of_scanned = numScanned;
        //rank_high_qr = rankHighQR;
        //rank_num_of_scanned = rankNumScanned;
        //rank_sum_of_scores = rankSumScores;
    }

    /*public PlayerStats(String username) {
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
        }
    }*/

    /*
    public PlayerStats(Player player) {
        this.player = player;
    }
     */

    public int getHigh_qr() {
        return high_qr;
    }

    public void setHigh_qr(int high_qr) {
        this.high_qr = high_qr;
    }

    public int getLow_qr() {
        return low_qr;
    }

    public void setLow_qr(int low_qr) {
        this.low_qr = low_qr;
    }

    public int getSum_of_scores() {
        return sum_of_scores;
    }

    public void setSum_of_scores(int sum_of_scores) {
        this.sum_of_scores = sum_of_scores;
    }

    public int getNum_of_scanned() {
        return num_of_scanned;
    }

    public void setNum_of_scanned(int num_of_scanned) {
        this.num_of_scanned = num_of_scanned;
    }

    public int getRank_num_of_scanned() {
        return rank_num_of_scanned;
    }

    public void setRank_num_of_scanned(int rank_num_of_scanned) {
        this.rank_num_of_scanned = rank_num_of_scanned;
    }

    public int getRank_high_qr() {
        return rank_high_qr;
    }

    public void setRank_high_qr(int rank_high_qr) {
        this.rank_high_qr = rank_high_qr;
    }

    public int getRank_sum_of_scores() {
        return rank_sum_of_scores;
    }

    public void setRank_sum_of_scores(int rank_sum_of_scores) {
        this.rank_sum_of_scores = rank_sum_of_scores;
    }
}
