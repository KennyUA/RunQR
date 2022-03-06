package com.example.runqr;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class QRLibraryActivity extends AppCompatActivity {

    ListView QRList;
    ArrayAdapter<QRCode> QRAdapter;
    QRLibrary QRDataList;
    Account playerAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrlibrary);

        // get intent
        // To retrieve object in second Activity
        QRLibrary playerQRLibrary = (QRLibrary) getIntent().getSerializableExtra("Player QRLibrary");

        QRList = findViewById(R.id.qrlibrary_list);

        //QRDataList = new ArrayList<QRCode>(); //convert string list to arraylist
        QRDataList = playerQRLibrary;


        QRAdapter = new QRList(this, QRDataList);

        QRList.setAdapter(QRAdapter);


        // Code below borrows from: https://stackoverflow.com/questions/4709870/setonitemclicklistener-on-custom-listview
        QRList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Button deleteButton = (Button) findViewById(R.id.delete_qr_button);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Object listCity = cityList.getItemAtPosition(position);
                        QRCode QRCodeToDelete = QRDataList.getQRCode(position);
                        QRDataList.deleteQRCode(QRCodeToDelete);
                        //QRList.setAdapter(QRAdapter);
                        QRAdapter.notifyDataSetChanged();

                    }
                });

            }


        });

    }






    }
