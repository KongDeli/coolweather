package com.example.kdl.coolweather.gson;

/**
 * Created by kdl on 2017/7/30.
 */

public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
