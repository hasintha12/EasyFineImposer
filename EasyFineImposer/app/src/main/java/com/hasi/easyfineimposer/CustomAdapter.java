package com.hasi.easyfineimposer;

/**
 * Created by hasintha on 9/2/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<FineData> {

    CustomAdapter(Context context, FineData[] fData) {
        super(context, R.layout.custom_row ,fData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View customView = Inflater.inflate(R.layout.custom_row, parent, false);

        String name = getItem(position).getName();
        String nic = getItem(position).getNic();
        String vehicleNo = getItem(position).getVehicleNo();
        String amount = getItem(position).getAmount();
        String description = getItem(position).getDescription();
        String location=getItem(position).getLocation();
        String dateTime=getItem(position).getDateTime();


        TextView nameText = (TextView) customView.findViewById(R.id.nameText);
        TextView nicText = (TextView) customView.findViewById(R.id.nicText);
        TextView vehicleText = (TextView) customView.findViewById(R.id.vehicleText);
        TextView amountText = (TextView) customView.findViewById(R.id.amountText);
        TextView descriptionText = (TextView) customView.findViewById(R.id.descriptionText);
        TextView locationText = (TextView) customView.findViewById(R.id.locationText);
        TextView timeDateText = (TextView) customView.findViewById(R.id.timeText);


        nameText.setText(name);
        nicText.setText(nic);
        vehicleText.setText(vehicleNo);
        amountText.setText(amount);
        descriptionText.setText(description);
        locationText.setText(location);
        timeDateText.setText(dateTime);

        return customView;
    }
}