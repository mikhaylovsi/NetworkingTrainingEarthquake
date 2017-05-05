package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by Sergei on 27.04.2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String url;
    public static final String TAG = "EarthquakeLoader";

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.w(EarthquakeActivity.LOG_TAG, "Loader was starting loading");
    }

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {

        Log.w(EarthquakeActivity.LOG_TAG, "Start to fetch data");
        List<Earthquake> earthquakes = QueryUtils.fetchDataFromURL(url);
        Log.w(EarthquakeActivity.LOG_TAG, "Finish data fetching");
        return earthquakes;
    }
}
