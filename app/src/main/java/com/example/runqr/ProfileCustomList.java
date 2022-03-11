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


public class ProfileCustomList extends ArrayAdapter<ProfileItem> {

    private ArrayList<ProfileItem> profile_items;
    private Context context;

    public ProfileCustomList(Context context, ArrayList<ProfileItem> profile_items){
        super(context,0,profile_items);
        this.profile_items = profile_items;
        this.context = context;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view==null){

            view = LayoutInflater.from(context).inflate(R.layout.profile_content, parent, false);

        }

        ProfileItem profileItem = profile_items.get(position);

        /*uncomment following four lines when resource file made*/
        TextView itemName = view.findViewById(R.id.item_text);
        TextView itemValue = view.findViewById(R.id.value_text);
        itemName.setText(profileItem.getItem());
        itemValue.setText(profileItem.getValue());

        return view;
    }

}
