package com.example.runqr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LeaderboardCustomList extends ArrayAdapter<LeaderboardItem> {

    private ArrayList<LeaderboardItem> leaderboard_items;
    private Context context;

    public LeaderboardCustomList(Context context, ArrayList<LeaderboardItem> leaderboard_items){
        super(context,0,leaderboard_items);
        this.leaderboard_items = leaderboard_items;
        this.context = context;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view==null){
            /*uncomment when resource file made*/
            //view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);

        }

        LeaderboardItem leaderboardItem = leaderboard_items.get(position);

        /*uncomment following four lines when resource file made*/
        //TextView playerName = view.findViewById(R.id.player_name);
        //TextView playerScore = view.findViewById(player_score);
        //playerName.setText(leaderboardItem.getPlayer());
        //playerScore.setText(leaderboardItem.getScore());

        return view;
    }

}
