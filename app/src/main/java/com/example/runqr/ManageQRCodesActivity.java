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

public class ManageQRCodesActivity extends AppCompatActivity {

    private ListView codeList;
    private ArrayAdapter<Integer> codeAdapter;
    private ArrayList<Integer> dataList;
    private ArrayList<String> scannedByList;
    private boolean confirmClicked = false;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_qrcodes);


        db = FirebaseFirestore.getInstance();
        final CollectionReference ref = db.collection("Accounts");// asynchronously retrieve all documents

        Intent intent = getIntent();
        dataList = intent.getIntegerArrayListExtra("list of QRCodes");


        codeList = findViewById(R.id.codes_list);


        codeAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        codeList.setAdapter(codeAdapter);
        /** handles the deleting feature **/
        codeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmClicked = true;
                //dataList.remove(position);
                final Button button = (Button) findViewById(R.id.confirm_button);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if(confirmClicked){
                            db.collection("QR Codes").document(dataList.get(position).toString())
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
                            scannedByList = (ArrayList<String>) db.collection("QR Codes").document(dataList.get(position)).get("Scanned by");



                            dataList.remove(position);
                            codeList.setAdapter(codeAdapter);
                        }
                        confirmClicked = false;


                    }
                });

            }
        });






















    }
}