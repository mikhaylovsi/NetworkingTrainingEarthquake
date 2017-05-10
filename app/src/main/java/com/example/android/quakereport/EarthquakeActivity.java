/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;

    EarthquakeAdapter mAdapter;

    ListView earthquakeListView;

    TextView emptyView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        emptyView = (TextView) findViewById(R.id.emptyList);
        earthquakeListView.setEmptyView(emptyView);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        earthquakeListView.setOnItemClickListener( new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = ((Earthquake)parent.getItemAtPosition(position)).getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        earthquakeListView.setAdapter(mAdapter);


        Log.w(LOG_TAG, "Loader was initialized");
        getSupportLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);

    }

    @Override
    public android.support.v4.content.Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.w(LOG_TAG, "Loader was created");
        return new EarthquakeLoader(this, URL);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Earthquake>> loader, List<Earthquake> data) {

       mAdapter.clear();
       //mAdapter.addAll(data);
        progressBar.setVisibility(View.GONE);

        emptyView.setText(R.string.empty_view);

        Log.w(LOG_TAG, "Loader was finished");



    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Earthquake>> loader) {


        mAdapter.clear();
        Log.w(LOG_TAG, "Loader was cleared");

    }


}
