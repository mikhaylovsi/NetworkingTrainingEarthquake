package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Sergei on 03.04.2017.
 */

public class Earthquake {

    double magnitude;
    String city;
    Date date;

    public Earthquake(double magnitude, String city, Date date){

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

    public Date getDate() {
        return date;
    }

}
