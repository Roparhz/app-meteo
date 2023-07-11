package com.julien.leveque.cdaproject.models;


import org.json.JSONException;
import org.json.JSONObject;

public class City {

    public String mName;
    public String mDescription;
    public String mTemperature;
    public int mWeatherIcon;

    public int mIdCity;
    public double mLatitude, mLongitude;
    public int mWeatherResIconWhite;

    public City(String name, String description, String temperature, int weatherIcon) {
        mName = name;
        mDescription = description;
        mTemperature = temperature;
        mWeatherIcon = weatherIcon;
    }

    public City (String strJson) throws JSONException {
        JSONObject json = new JSONObject(strJson);
        mName = json.getString("name");
        mDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
        mTemperature = json.getJSONObject("main").getString("temp");
    }

}
