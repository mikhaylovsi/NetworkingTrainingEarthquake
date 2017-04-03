package com.example.android.quakereport;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Sergei on 03.04.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter{



    public EarthquakeAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Earthquake> Earthquakes) {
        super(context, resource);
    }



}
