package com.example.runqr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManageQRCodesActivity extends AppCompatActivity {

    private ListView codeList;
    private ArrayAdapter<Integer> codeAdapter;
    private ArrayList<Integer> dataList;
    private boolean confirmClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_qrcodes);

        Intent intent = getIntent();
        dataList = intent.getIntegerArrayListExtra("list of QRCodes");


        codeList = findViewById(R.id.codes_list);

        //String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna"};

        //dataList = new ArrayList<>();

        //dataList.addAll(Arrays.asList(cities));

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