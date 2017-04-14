package com.example.android.quakereport;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
            viewHolder.tvDistance = (TextView)listItemView.findViewById(R.id.distance);
            viewHolder.tvCity = (TextView)listItemView.findViewById(R.id.city);
            viewHolder.tvDate = (TextView)listItemView.findViewById(R.id.date);
            viewHolder.tvTime = (TextView)listItemView.findViewById(R.id.time);

            listItemView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        SimpleDateFormat timeFormat = new SimpleDateFormat("K:mm a");

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        DecimalFormatSymbols dfs = decimalFormat.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(dfs);

        String decimalMagnitude = decimalFormat.format(earthquake.getMagnitude());

        GradientDrawable background = (GradientDrawable) viewHolder.tvMagnitude.getBackground();
        background.setColor(getMagnitudeColor(earthquake.getMagnitude()));

        viewHolder.tvMagnitude.setText(decimalMagnitude);

        if (earthquake.getCity().contains(" of ")){

            String distance = earthquake.getCity().substring(0, earthquake.getCity().indexOf(" of ") + 3);
            String city = earthquake.getCity().substring(earthquake.getCity().indexOf(" of ") + 4);
            viewHolder.tvCity.setText(city);
            viewHolder.tvDistance.setText(distance);
        }
        else {
            String city = earthquake.getCity();
            viewHolder.tvCity.setText(city);
            viewHolder.tvDistance.setText(context.getString(R.string.nearThe));
        }


        viewHolder.tvDate.setText(dateFormat.format(earthquake.getDate()));
        viewHolder.tvTime.setText(timeFormat.format(earthquake.getDate()));

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude){

        int colorId;

        switch((int)magnitude){
            case 0:
            case 1:
                colorId = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 2:
                colorId = ContextCompat.getColor(context, R.color.magnitude2);
                break;
            case 3:
                colorId = ContextCompat.getColor(context, R.color.magnitude3);
                break;
            case 4:
                colorId = ContextCompat.getColor(context, R.color.magnitude4);
                break;
            case 5:
                colorId = ContextCompat.getColor(context, R.color.magnitude5);
                break;
            case 6:
                colorId = ContextCompat.getColor(context, R.color.magnitude6);
                break;
            case 7:
                colorId = ContextCompat.getColor(context, R.color.magnitude7);
                break;
            case 8:
                colorId = ContextCompat.getColor(context, R.color.magnitude8);
                break;
            case 9:
                colorId = ContextCompat.getColor(context, R.color.magnitude9);
                break;
            default:
                colorId = ContextCompat.getColor(context, R.color.magnitude10plus);
        }

        return colorId;
    }

    private class ViewHolder{

         TextView tvMagnitude;
         TextView tvCity;
         TextView tvDistance;
         TextView tvDate;
         TextView tvTime;

    }

}
