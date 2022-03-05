package com.example.runqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    Boolean emailExists;
    Boolean usernameExists;
    FirebaseFirestore db;
    Button loginQR;
    Button signupButton;
    TextView errorLength;
    EditText email;
    EditText username;
    Player currentPlayer;
    final String TAG = "Sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameExists = Boolean.FALSE;
        emailExists = Boolean.FALSE;
        loginQR = findViewById(R.id.login_qr_button);
        signupButton = findViewById(R.id.signup_button);
        email = findViewById(R.id.email);
        errorLength = findViewById(R.id.sizeError);
        username = findViewById(R.id.username);
        db = FirebaseFirestore.getInstance();


        final CollectionReference collectionReference = db.collection("Accounts");

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String usernameText=username.getText().toString();
                    if(usernameText.length()<=5){
                        errorLength.setVisibility(View.VISIBLE);
                    }
                    else {
                        errorLength.setVisibility(View.INVISIBLE);
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference userNameRef = rootRef.child("Accounts").child(usernameText);
                        ValueEventListener eventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (!dataSnapshot.exists()) {
                                    usernameExists = Boolean.TRUE;
                                }
                                else{
                                    usernameExists = Boolean.FALSE;
                                    errorLength.setText("This username is used");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        };
                        userNameRef.addListenerForSingleValueEvent(eventListener);
                    }
                }
            }
        });

        signupButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameData = username.getText().toString();
                final String emailData = email.getText().toString();
                HashMap<String, String> data = new HashMap<>();
                if (usernameExists) {
                    data.put("Email", emailData);
                    PlayerStats stats = new PlayerStats();
                    Account newAccount = new Account(usernameData, stats, emailData);
                    currentPlayer = new Player(newAccount);
                    collectionReference
                            .document(usernameData)
                            .set(emailData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
// These are a method which gets executed when the task is succeeded
                                    Log.d(TAG, "Data has been added successfully!");
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
// These are a method which gets executed if there’s any problem
                                    Log.d(TAG, "Data could not be added!" + e.toString());
                                }
                            });
                }
            }
        });



    }
}