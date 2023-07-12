package com.julien.leveque.cdaproject.models;


import com.julien.leveque.cdaproject.utils.Util;

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


    public City (String strJson) throws JSONException {
        JSONObject json = new JSONObject(strJson);
        JSONObject details = json.getJSONArray("weather").getJSONObject(0);
        JSONObject main = json.getJSONObject("main");
        JSONObject coord = json.getJSONObject("coord");

        mIdCity = json.getInt("id");
        mName = json.getString("name");
        mDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
        mTemperature = json.getJSONObject("main").getString("temp");
        mWeatherIcon = Util.setWeatherIcon(details.getInt("id"), json.getJSONObject("sys").getLong("sunrise") * 1000, json.getJSONObject("sys").getLong("sunset") * 1000);
        mLatitude = coord.getDouble("lat");
        mLongitude = coord.getDouble("lon");
    }

}
