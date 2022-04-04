package com.example.runqr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

// This class is the DialogFragment which allows players to add and edit comments to the QRCode in DisplayQRCodeActivity.
// The newly created comment is passed back to DisplayQRCodeActivity through method onOkPressed and added to QRCode's CommentLibrary there.


public class AddCommentFragment extends DialogFragment {

    private EditText commentTitle;
    private EditText commentBody;

    private OnFragmentInteractionListener listener;
    //private City city = null;

    public AddCommentFragment () {
        super();

    }


    public interface OnFragmentInteractionListener {
        void onOkPressed(Comment newComment);

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString()+
                    " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_comment_fragment_layout, null);

        commentBody= view.findViewById(R.id.comment_body_editText);
        //commentTitle = view.findViewById(R.id.comment_title_editText);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit Comment")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String body = commentBody.getText().toString();

                        //String title = commentTitle.getText().toString();
                        //listener.onOkPressed(new Comment(title, body));
                        if (!commentBody.getText().toString().equals("")) {
                            // add comment to commentlibrary
                            listener.onOkPressed(new Comment(body));
                        }
                        else {
                            Toast.makeText(getActivity(), "Please enter a comment", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).create();
    }

}
