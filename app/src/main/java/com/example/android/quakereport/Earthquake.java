package com.example.android.quakereport;

/**
 * Created by Sergei on 03.04.2017.
 */

public class Earthquake {

    double magnitude;
    String city;
    String date;

    public Earthquake(float magnitude, String city, String date){

        this.magnitude = magnitude;
        this.city = city;
        this.date = date;

    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

}
