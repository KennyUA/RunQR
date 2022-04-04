package com.example.runqr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class acts as an adapter for the RecyclerView that functions to display the list of comments in the comment library.
 * This list of comments is displayed in the DisplayQRCodeActivity.
 * Below is constructor and overriden methods from extending RecyclerView.Adapter.
 */
public class CommentList extends RecyclerView.Adapter<CommentList.ViewHolder> implements Serializable {

    //private QRLibrary QRCodes;
    private ArrayList<Comment> comments;
    private Context context;
    private ItemClickListener mItemListener;


    //public QRList(Context context, QRLibrary QRCodes){
    public CommentList(Context context, ArrayList<Comment> comments, ItemClickListener itemClickListener){
        //super(context,0, (List<String>) comments);
        this.context = context;
        this.comments = comments;
        this.context = context;
        this.mItemListener = itemClickListener;
    }



    @NonNull
    @Override
    public CommentList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentlibrary_content, parent, false);

        // Passing view to ViewHolder
        CommentList.ViewHolder viewHolder = new CommentList.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull CommentList.ViewHolder holder, int position) {
        //set's body and title of comment
        holder.bodyText.setText((String) comments.get(position).getBody());
        //holder.titleText.setText((String) comments.get(position).getTitle());

        // Set on-click listener
        holder.bodyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onItemClick(comments.get(holder.getAdapterPosition()));
                //Comment commentClicked = comments.get(position);
                //onCommentClick(commentClicked);

                //new AddCommentFragment().show(getSupportFragmentManager(), "EDIT_CITY");

            }
        });

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(comments.get(position));
            // above will get the item clicked in recyclerview
        });
    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return comments.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bodyText;
        //TextView titleText;

        public ViewHolder(View view) {
            super(view);
            bodyText = (TextView) view.findViewById(R.id.qrcode_comment_body_text);
            //titleText = (TextView) view.findViewById(R.id.qrcode_comment_body_text);
        }
    }


    public interface ItemClickListener{
        void onItemClick(Comment commentClicked);
    }

}
