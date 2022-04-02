package com.example.runqr;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

// This class is the activity that displays the details of QRCodes and allows player to browse their QRCodes.
// Once an item, corresponding to a QRCode, in ListView of QRLibraryActivity is clicked the DisplayQRActivity is opened to display QRCode details.
// QRCode details being displayed so far are: score, location (X and Y coordinates) if it exists, and image if it exists.


public class DisplayQRCodeActivity extends AppCompatActivity implements AddCommentFragment.OnFragmentInteractionListener {

    //for editing comments
    Boolean edit = false;
    Integer clickPos;
    Comment clickedComment = null;
    ArrayList<Comment> commentDataList;
    RecyclerView QRCodeComments;
    CommentList commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_qrcode);

        QRCode codeToDisplay = (QRCode) getIntent().getSerializableExtra("QRCode to display");

        ImageView QRCodePhoto = findViewById(R.id.photoInfo);
        TextView QRCodeStats = findViewById(R.id.statInfo);
        TextView QRCodeLocation = findViewById(R.id.locationInfo);
        FloatingActionButton addCommentButton = findViewById(R.id.add_comment_button);
        QRCodeComments = findViewById(R.id.recyclerView);
        commentDataList = codeToDisplay.getCommentLibrary().getCommentList();

        // Setting the layout as linear
        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        QRCodeComments.setLayoutManager(linearLayoutManager);

        // Sending reference and data to Adapter
        commentAdapter = new CommentList(DisplayQRCodeActivity.this, codeToDisplay.getCommentLibrary().getCommentList(),
                new CommentList.ItemClickListener() {
            @Override
            public void onItemClick(Comment commentClicked) {
                edit = true;
                clickedComment = commentClicked;
                new AddCommentFragment().show(getSupportFragmentManager(), "EDIT_CITY");
            }
        });
        QRCodeComments.setAdapter(commentAdapter);

        QRCodeLocation.setText("Location: \n");
        if (codeToDisplay.getLocation() != null){
            QRCodeLocation.append("X: "+codeToDisplay.getLocation().getX()+"\n"+"Y: "+codeToDisplay.getLocation().getY());

        }
        if (codeToDisplay.getPhoto() != null){
            //QRCodePhoto.setImageBitmap(codeToDisplay.getPhoto());

        }
        QRCodeStats.setText("Score: "+codeToDisplay.getScore());

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCommentFragment().show(getSupportFragmentManager(), "ADD_CITY");
            }
        });




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
            commentDataList.remove(clickedComment);
            commentDataList.add(newComment);
            commentAdapter.notifyDataSetChanged();
            //cityList.setAdapter(cityAdapter);
        }
        else {

            commentDataList.add(newComment);
            commentAdapter.notifyDataSetChanged();
        }
        QRCodeComments.scrollToPosition(commentDataList.size()-1);
        edit = false;

    }

}