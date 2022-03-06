package com.example.runqr;

import static java.sql.Types.NULL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    Boolean emailExists;
    Boolean usernameExists;
    FirebaseFirestore db;
    Button loginQR;
    Button signupButton;
    TextView usernameMessage;
    EditText email;
    TextView validEmailTextView;
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
        usernameMessage = findViewById(R.id.sizeError);
        username = findViewById(R.id.username);
        validEmailTextView = findViewById(R.id.validEmail);

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Accounts");
        username.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String usernameText=username.getText().toString();
                if(usernameText.length()<5){
                    usernameMessage.setText("Username should contain at least 5 symbols");
                    usernameMessage.setVisibility(View.VISIBLE);
                }
                else {
                    usernameMessage.setVisibility(View.INVISIBLE);
                    DocumentReference docRef = db.collection("Accounts").document(usernameText);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    usernameMessage.setText("This username is used");
                                    usernameMessage.setVisibility(View.VISIBLE);
                                    usernameExists = Boolean.FALSE;
                                    Log.d(TAG, "No such document");
                                } else {
                                    usernameExists = Boolean.TRUE;
                                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText=email.getText().toString();
                if(isValidEmailId(emailText.trim())){
                    emailExists = Boolean.TRUE;
                    validEmailTextView.setVisibility(View.INVISIBLE);
                }else{
                    validEmailTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        signupButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameData = username.getText().toString();
                final String emailData = email.getText().toString();

                Log.d(TAG, "Here!");
                HashMap<String, String> data = new HashMap<>();
                if (usernameExists && emailExists) {
                    data.put("Email", emailData);
                    PlayerStats stats = new PlayerStats();
                    Account newAccount = new Account(usernameData, stats, emailData);
                    currentPlayer = new Player(newAccount);
                    collectionReference
                            .document(usernameData)
                            .set(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
// These are a method which gets executed when the task is succeeded
                                    HashMap<String, Integer> statdata = new HashMap<>();
                                    statdata.put("number_of_scanned", 0);
                                    statdata.put("high_score", NULL);
                                    statdata.put("low_score", NULL);
                                    statdata.put("total_score", NULL);
                                    statdata.put("high_score_ranking", NULL);
                                    statdata.put("number_of_scanned_ranking", NULL);
                                    statdata.put("total_score_ranking", NULL);
                                    collectionReference
                                            .document(usernameData)
                                            .set(statdata)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
// These are a method which gets executed when the task is succeeded
                                                    Log.d(TAG, "Data has been added successfully!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
// These are a method which gets executed if there’s any problem
                                                    Log.d(TAG, "Data could not be added!" + e.toString());
                                                }
                                            });
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


        loginQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openAddQRFragment(loginQR);
            }
        });


    }

    public void openAddQRFragment(Button addQR){
        // open addQRFragment to scan QRcode and add it to player's account
        addQR.setVisibility(View.GONE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddQRFragment addQRFragment = new AddQRFragment();
        //fragmentTransaction.add(R.id.addQRFragment_container,addQRFragment);\
        fragmentTransaction.add(R.id.addQRFragment_container, addQRFragment, "Add QR Code");
        fragmentTransaction.commit();
        //addQR.setVisibility(View.VISIBLE);

        //getSupportFragmentManager().beginTransaction().add(R.id.container, new AddQRFragment()).commit();

        //final View addQR = findViewById(R.id.fragment_container_view);
        //addQR.setVisibility(View.VISIBLE);



        //AddQRFragment addQRFragment = new AddQRFragment();
        //FragmentManager manager = getFragmentManager();
        //FragmentTransaction transaction = manager.beginTransaction();
        //transaction.add(R.id.fragment_container_view,AddQRFragment.class,"OPEN_SCANNER");
        //transaction.replace(R.id.container,);
        //transaction.addToBackStack(null);
        //transaction.commit();

    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}