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

public class ManagePlayersActivity extends AppCompatActivity {

    private ListView playerList;
    private ArrayAdapter<String> playerAdapter;
    private ArrayList<String> dataList;
    private boolean confirmClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_players);

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