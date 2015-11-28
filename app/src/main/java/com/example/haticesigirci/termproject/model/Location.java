package com.example.haticesigirci.termproject.model;

import android.util.Log;


/**
 * Created by haticesigirci on 29/11/15.
 */
public class Location {

    /**
     * This is a tutorial source code
     * provided "as is" and without warranties.
     *
     * For any question please visit the web site
     * http://www.survivingwithandroid.com
     *
     * or write an email to
     * survivingwithandroid@gmail.com
     *
     */


    private String longitude;
    private String latitude;
    private String sunset;
    private String sunrise;
    private String country;
    private String city;

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getSunset() {
        return sunset;
    }
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    public String getSunrise() {
        return sunrise;
    }
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        Log.d("InsideLocation", country);
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        Log.d("InsideCity", city);
        this.city = city;
    }




}
