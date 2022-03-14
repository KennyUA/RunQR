package com.example.runqr;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomListAdapter extends ArrayAdapter<MarkerOptions> {

    private ArrayList<MarkerOptions> markerOptionsArray;
    private Context context;

    public CustomListAdapter(@NonNull Context context, ArrayList<MarkerOptions> markerOptionsArray) {
        super(context,0, markerOptionsArray);
        this.markerOptionsArray = markerOptionsArray;
        this.context = context;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent){
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_item, parent,false);
        }
        MarkerOptions options = markerOptionsArray.get(position);
        ImageView qrCodeImg = view.findViewById(R.id.qrCodeImg);
        TextView qrText = view.findViewById(R.id.qrCodeTextView);
        TextView locationText = view.findViewById(R.id.LocationTxtView);

        qrCodeImg.setImageResource(R.drawable.qr_code);
        List<Address> locationAddresses = null;
        qrText.setText(String.format("QR Code #%s", String.valueOf(position + 1)));
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            locationAddresses = geocoder.getFromLocation(options.getPosition().latitude, options.getPosition().longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String addressLine = locationAddresses.get(0).getAddressLine(0);
        String cityName = locationAddresses.get(0).getLocality();
        String provinceName = locationAddresses.get(0).getAdminArea();
        String countryName = locationAddresses.get(0).getCountryName();
        String postalCode = locationAddresses.get(0).getPostalCode();

        String markerAddress = String.format("%s", addressLine );
        locationText.setText(markerAddress);


        return view;

    }
}
