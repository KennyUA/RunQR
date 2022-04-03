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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ManagePlayersActivity extends AppCompatActivity {

    private ListView playerList;
    private ArrayAdapter<String> playerAdapter;
    private ArrayList<String> dataList;
    private boolean confirmClicked;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_players);

        db = FirebaseFirestore.getInstance();
        final CollectionReference ref = db.collection("Accounts");// asynchronously retrieve all documents

        playerList = findViewById(R.id.users_list);

        //String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna"};

        Intent intent = getIntent();
        dataList = (ArrayList<String>) intent.getStringArrayListExtra("list of players");





        playerAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        playerList.setAdapter(playerAdapter);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmClicked = true;
                final Button button = (Button) findViewById(R.id.confirm_button);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if(confirmClicked){
                            db.collection("Accounts").document(dataList.get(position))
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

                            playerList.setAdapter(playerAdapter);
                        }
                        confirmClicked = false;

                    }
                });

            }
        });



    }
}