package com.example.runqr;

public class PlayerStats {
    private Player player;

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
}
