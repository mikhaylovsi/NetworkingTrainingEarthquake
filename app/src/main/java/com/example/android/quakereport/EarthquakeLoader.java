package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Sergei on 27.04.2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String url;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        List<Earthquake> earthquakes = QueryUtils.fetchDataFromURL(url);
        return earthquakes;
    }
}
