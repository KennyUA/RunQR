package com.example.runqr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.runqr.placeholder.PlaceholderContent;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class MarkerFragment extends DialogFragment {


    private ListView locationList;
    ArrayAdapter<MarkerOptions> locationsAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarkerFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MarkerFragment newInstance(ArrayList<MarkerOptions> MarkerArray) {
        MarkerFragment fragment = new MarkerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("markerArray", MarkerArray);
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_fragment, null);
        locationList = view.findViewById(R.id.qrListView);

        //cite https://camposha.info/android-examples/android-list-fragment/#gsc.tab=0
        ArrayList<MarkerOptions> locationsList = getArguments().getParcelableArrayList("markerArray");

        locationsAdapter = new CustomListAdapter(getContext(), locationsList);
        locationList.setAdapter(locationsAdapter);
        locationList.setEmptyView(view.findViewById(R.id.noQRCodesNearbyView));

        AlertDialog.Builder  builder = new AlertDialog.Builder((getContext()));
        return builder
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", null).create();

    }
}