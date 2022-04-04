package com.example.runqr;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

// This class is the Activity used to allow owners to delete malicious QRCodes from the game and it deletes directly from firestore.

public class ManageQRCodesActivity extends AppCompatActivity {

    private ListView codeList;
    private ArrayAdapter<String> codeAdapter;
    private ArrayList<String> dataList;
    private ArrayList<Player> scannedByList = new ArrayList<Player>();
    private boolean confirmClicked = false;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_qrcodes);


        db = FirebaseFirestore.getInstance();
        final CollectionReference ref = db.collection("Accounts");// asynchronously retrieve all documents

        Intent intent = getIntent();
        dataList = intent.getStringArrayListExtra("list of QRCodes");


        codeList = findViewById(R.id.codes_list);


        codeAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        codeList.setAdapter(codeAdapter);
        /** handles the deleting feature **/
        codeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmClicked = true;
                //dataList.remove(position);
                String codeHash = dataList.get(position);
                final Button button = (Button) findViewById(R.id.confirm_button);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if(confirmClicked){
                            db.collection("QR Codes").document(codeHash.toString())
                                    .collection("Players")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                    scannedByList.add(document.toObject(Player.class));

                                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                                }
                                            } else {
                                                Log.d(TAG, "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });


                            for(int i =0; i< scannedByList.size();i++){
                                Player player = scannedByList.get(i);
                                player.getPlayerQRLibrary().deleteQRCodeWithHash(codeHash);

                                //CALL BAILEYS FUNCTION TO UPDATE PLAYER STATS

                            }

                            db.collection("QR Codes").document(codeHash.toString())
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error deleting document", e);
                                        }
                                    });




                            dataList.remove(position);
                            codeList.setAdapter(codeAdapter);
                        }
                        confirmClicked = false;


                    }
                });

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        //intent.putExtra("Player QRLibrary", playerQRLibrary);
        intent.putExtra("CodeList updated", dataList);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}