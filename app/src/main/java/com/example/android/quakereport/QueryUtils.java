package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {


    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     * @param jsonResponse
     */
    public static ArrayList<Earthquake> extractEarthquakes(String jsonResponse) {

        if(jsonResponse == null || jsonResponse.isEmpty()){
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("features");
            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObjectEarthquake = jsonArray.getJSONObject(i);
                JSONObject jsonObjectEarthquakeProperties = jsonObjectEarthquake.getJSONObject("properties");

                double magnitude = jsonObjectEarthquakeProperties.getDouble("mag");

                String city = jsonObjectEarthquakeProperties.getString("place");

                long time = jsonObjectEarthquakeProperties.getLong("time");
                Date date = new Date(time);

                String url = jsonObjectEarthquakeProperties.getString("url");

                Earthquake earthquake = new Earthquake(magnitude, city, date, url);
                earthquakes.add(earthquake);


            }



        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    public static List<Earthquake> fetchDataFromURL(String urlString) {



       URL url = makeURL(urlString);
        String jsonResponse = "";

        List<Earthquake> earthquakes = null;

        try {
            jsonResponse = makeHTTPRequest(url);
            earthquakes = extractEarthquakes(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }

    private static String makeHTTPRequest(URL url) throws IOException {

        if(url == null){
            return null;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        String jsonResponse = null;

        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();
            inputStream = connection.getInputStream();
            jsonResponse = convertInputStreamToString(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(connection != null){
                connection.disconnect();
            }

            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String convertInputStreamToString(InputStream inputStream) {

        String jsonResponse = "";
        StringBuilder stringBuilder = null;


        try {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            stringBuilder = new StringBuilder();

            String line = bufferedReader.readLine();
            while(!TextUtils.isEmpty(line)){
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

            jsonResponse = stringBuilder.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonResponse;

    }

    private static URL makeURL(String urlString) {

        URL url = null;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }
}