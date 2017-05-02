package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Sergei on 27.04.2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String url;

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

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
