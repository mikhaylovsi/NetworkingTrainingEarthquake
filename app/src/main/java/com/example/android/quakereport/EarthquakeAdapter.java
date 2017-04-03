package com.example.android.quakereport;


import android.app.Activity;
import android.content.Context;
import android.icu.util.TimeZone;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by Sergei on 03.04.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{


    private ArrayList<Earthquake> earthquakes;
    private Activity context;


    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, R.layout.earthquake_list_item, earthquakes);
        this.earthquakes = earthquakes;
        this.context = (Activity)context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Earthquake earthquake;
        earthquake = getItem(position);
        ViewHolder viewHolder;

        View listItemView = convertView;
        if(listItemView == null){

            LayoutInflater inflater = context.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.earthquake_list_item, null, true);

            viewHolder = new ViewHolder();

            viewHolder.tvMagnitude = (TextView)listItemView.findViewById(R.id.magnitude);
            viewHolder.tvCity = (TextView)listItemView.findViewById(R.id.city);
            viewHolder.tvDate = (TextView)listItemView.findViewById(R.id.date);

            listItemView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);

        viewHolder.tvMagnitude.setText(String.valueOf(earthquake.getMagnitude()));
        viewHolder.tvCity.setText(earthquake.getCity());
        viewHolder.tvDate.setText(dateFormat.format(earthquake.getDate()));

        return listItemView;
    }

    private class ViewHolder{

         TextView tvMagnitude;
         TextView tvCity;
         TextView tvDate;

    }

}
