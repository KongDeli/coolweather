package com.example.kdl.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kdl on 2017/7/30.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
