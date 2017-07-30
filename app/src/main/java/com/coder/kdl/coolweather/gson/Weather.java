package com.example.kdl.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kdl on 2017/7/30.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
