package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Sergei on 03.04.2017.
 */

public class Earthquake {

    double magnitude;
    String city;
    Date date;
    String url;

    public Earthquake(double magnitude, String city, Date date, String url){

        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
        this.url = url;

    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

}
