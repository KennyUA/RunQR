package com.example.runqr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

// This class is the Activity used to allow owners to select whether they want to manage QRCodes or Players.
// This activity is opened from LoginActivity and takes owners to ManageQRCodesActivity & ManagePlayersActivity.

public class OwnerActivity extends AppCompatActivity {

    FirebaseFirestore db;

    Button managePlayers;
    Button manageCodes;
    EditText stuff;
    final String TAG = "Sample";
    ArrayList<String> userList = new ArrayList<String> ();
    ArrayList<String> codesList = new ArrayList<String> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        managePlayers = findViewById(R.id.manage_players_button);
        manageCodes = findViewById(R.id.manage_codes_button);
        //stuff = findViewById(R.id.player_info);

        db = FirebaseFirestore.getInstance();
        final CollectionReference ref = db.collection("Accounts");// asynchronously retrieve all documents

        db.collection("Accounts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                userList.add(document.getId().toString());
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        db.collection("QR Codes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                codesList.add((document.getId()));
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        managePlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playerIntent = new Intent(OwnerActivity.this, ManagePlayersActivity.class);
                playerIntent.putStringArrayListExtra("list of players",userList);
                //startActivity(playerIntent);
                startActivityForResult(playerIntent, 1);

            }
        });
        manageCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OwnerActivity.this, ManageQRCodesActivity.class);
                intent.putStringArrayListExtra("list of QRCodes",codesList);
                //startActivity(intent);
                startActivityForResult(intent, 2);



            }
        });



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                //QRLibrary updatedQRLibrary = (QRLibrary) data.getSerializableExtra("Player QRLibrary");
                //Player updatedCurrentPlayer = (Player) data.getSerializableExtra("Player QRLibrary Updated");
                ArrayList<String> updatedUserList = (ArrayList<String>) data.getSerializableExtra("UserList updated");
                userList = updatedUserList;

            }
        }
        else if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                ArrayList<String> updatedCodesList = (ArrayList<String>) data.getSerializableExtra("CodeList updated");
                codesList = updatedCodesList;

            }
        }
    }


}