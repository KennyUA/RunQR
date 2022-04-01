package com.example.runqr;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// This class is the activity that displays the details of QRCodes and allows player to browse their QRCodes.
// Once an item, corresponding to a QRCode, in ListView of QRLibraryActivity is clicked the DisplayQRActivity is opened to display QRCode details.
// QRCode details being displayed so far are: score, location (X and Y coordinates) if it exists, and image if it exists.


public class DisplayQRCodeActivity extends AppCompatActivity implements AddCommentFragment.OnFragmentInteractionListener {

    //for editing comments
    Boolean edit = false;
    Integer clickPos;
    Comment clickedComment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_qrcode);

        QRCode codeToDisplay = (QRCode) getIntent().getSerializableExtra("QRCode to display");

        ImageView QRCodePhoto = findViewById(R.id.photoInfo);
        TextView QRCodeStats = findViewById(R.id.statInfo);
        TextView QRCodeLocation = findViewById(R.id.locationInfo);
        RecyclerView QRCodeComments = findViewById(R.id.recyclerView);
        ArrayList<Comment> commentDataList = codeToDisplay.getCommentLibrary().getCommentList();

        // Setting the layout as linear
        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        QRCodeComments.setLayoutManager(linearLayoutManager);

        // Sending reference and data to Adapter
        CommentList commentAdapter = new CommentList(DisplayQRCodeActivity.this, codeToDisplay.getCommentLibrary().getCommentList());
        QRCodeComments.setAdapter(commentAdapter);

        QRCodeLocation.setText("Location: \n");
        if (codeToDisplay.getLocation() != null){
            QRCodeLocation.append("X: "+codeToDisplay.getLocation().getX()+"\n"+"Y: "+codeToDisplay.getLocation().getY());

        }
        if (codeToDisplay.getPhoto() != null){
            //QRCodePhoto.setImageBitmap(codeToDisplay.getPhoto());

        }
        QRCodeStats.setText("Score: "+codeToDisplay.getScore());

        /*
        //add on item click listener for recycler view

        QRCodeComments.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                edit = true;
                clickPos = position;
                clickedComment = (Comment) cityList.getItemAtPosition(position);
                new AddCityFragment().show(getSupportFragmentManager(), "EDIT_CITY");


            }
        });

         */
    }

    @Override
    public void onOkPressed(Comment newComment) {
        if (edit) {
            //cDataList.remove(clickPos);
            //cityList.setAdapter(cityAdapter);
            //commentAdapter.remove(clickedComment);
            cityAdapter.add(newCity);
            //cityList.setAdapter(cityAdapter);
        }
        else {
            cityAdapter.add(newCity);
        }
        edit = false;

    }
}