package com.example.runqr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;

// Main activity of the RunQR game has an app bar with 2 icons: dropdown menu and an add QR Button which opens scanner for player to scan QRCodes.
// Main activity also contains a map with a refresh button and a nearbySearch button (AYUSH can elaborate on this).


public class MainActivity extends AppCompatActivity implements AddQRFragment.OnFragmentInteractionListener, OnMapReadyCallback {

    /// fix below to do automatic log in and save player info
    Player currentPlayer ;

    final String TAG = "Sample";

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db;
    static CollectionReference collectionReference;
    static CollectionReference QRCodesReference;
    // Create HashMaps to store QRCode and account data in Firestore.
    static HashMap<String, String> qrData = new HashMap<>();
    static HashMap<String, String> accountData = new HashMap<>();

    SupportMapFragment mapFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPlayer();
        db = FirebaseFirestore.getInstance();
        // Get a top level reference to the collection
        collectionReference = db.collection("Accounts");
        //HashMap<String, String> accountData = new HashMap<>();

        // Creating collection for global QRCodes
        QRCodesReference = db.collection("QR Codes");
        //HashMap<String, String> qrData = new HashMap<>();

        /*
        HashMap<String, Account> accountData = new HashMap<>();
        //HashMap<String, String> accountData = new HashMap<>();
        //accountData.put("Account", currentPlayer.getPlayerAccount().getUsername());


        // The set method sets a unique id for the document
        collectionReference
                .document("test_username")
                .set(accountData)
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



         */



        // The set method sets a unique id for the document
        //HashMap<String, String> accountData = new HashMap<>();
        //accountData.put("Account Username", currentPlayer.getPlayerAccount().getUsername());
        accountData.put("Account Username", "test_username");
        Log.v("Hello", "test_message");
        collectionReference
                .document("Usernames")
                .set(accountData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // These are a method which gets executed when the task is succeeded

                        Log.v(TAG, "Data has been added successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // These are a method which gets executed if there’s any problem
                        Log.v(TAG, "Data could not be added!" + e.toString());
                    }
                });
        Log.v("Hello", "test_message");





        db = FirebaseFirestore.getInstance();
        // Get a top level reference to the collection
        collectionReference = db.collection("Accounts");
        //HashMap<String, String> accountData = new HashMap<>();

        // Creating collection for global QRCodes
        QRCodesReference = db.collection("QR Codes");
        //HashMap<String, String> qrData = new HashMap<>();

        //Have to cite the https://developers.google.com/maps/documentation/android-sdk/map





        /*
        final Button addQR = findViewById(R.id.add_qr_button);
        addQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openAddQRFragment(addQR);

            }


        });


        */

    }

    @Override
    public void onStart(){
        super.onStart();

    }
    @Override
    public void onStop(){
        super.onStop();

    }
    @Override
    public void onLowMemory(){
        super.onLowMemory();

    }

    @Override
    public void onResume(){
        super.onResume();

    }


    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    void savePlayer(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json =gson.toJson(currentPlayer);
        editor.putString("player", json);
        editor.apply();
    }

    void loadPlayer(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("player", null);
        Type type = new TypeToken<Player>(){}.getType();
        currentPlayer = gson.fromJson(json, type);
    }



//    public void openAddQRFragment(Button addQR){
     public void openAddQRFragment(){
        // open addQRFragment to scan QRcode and add it to player's account
        //addQR.setVisibility(View.GONE);
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



    @Override
    public void onConfirmPressed(QRCode qrCodeData) {
        //String test = qrCodeData.getHash();

        currentPlayer.getPlayerQRLibrary().addQRCode(qrCodeData);

        // THIS NEEDS TO BE UPDATED BY KENNY
        // Below: open activity/fragment which prompts user to access their device's location and take photo of the object containing scannedQRCode




        // call method to add location data to qrCodeCollection for nearbyQRCodeSearch algorithm
        if (qrCodeData.getLocation() != null) { // and if it's not in the database
            addQRLocationGlobally(qrCodeData, qrData, QRCodesReference );

        }

    }

    public void addQRLocationGlobally(QRCode qrCodeData,HashMap<String, String> qrData, CollectionReference QRCodesReference ) {
        // Creating collection for global QRCodes
        //final CollectionReference QRCodesReference = db.collection("QR Codes");
        //HashMap<String, String> qrData = new HashMap<>();

        qrData.put("Location_X", String.valueOf(qrCodeData.getLocation().getX()));
        qrData.put("Location_Y", String.valueOf(qrCodeData.getLocation().getY()));

        QRCodesReference
                .document(qrCodeData.getHash())
                .set(qrData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // These are a method which gets executed when the task is succeeded

                        Log.v(TAG, "Global QRData has been added successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // These are a method which gets executed if there’s any problem
                        Log.v(TAG, "Global QRData could not be added!" + e.toString());
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.qr_library_item:
                //Open activity to show QRLibrary
                Intent intent = new Intent(this, QRLibraryActivity.class);
                intent.putExtra("Player QRLibrary", (Serializable) currentPlayer.getPlayerQRLibrary());
                startActivity(intent);
            case R.id.add_qr_item:
                //Open fragment to scan QR code
                openAddQRFragment();

            // TO OPEN LEADERBOARD ACTIVITY add code below


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.5232, -113.5263))
                .title("UofA"));
        //cite https://stackoverflow.com/questions/57096105/google-map-not-centered-in-desired-location
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(53.631611, -113.323975)).zoom(9).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
    }

