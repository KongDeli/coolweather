package com.example.kdl.coolweather.util;

import android.text.TextUtils;

import com.example.kdl.coolweather.db.City;
import com.example.kdl.coolweather.db.County;
import com.example.kdl.coolweather.db.Province;
import com.example.kdl.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kdl on 2017/7/29.
 */

public class Utility {
    /*
    解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    解析和处理服务器返回的县级数据
     */

    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
