package com.mafia.inter.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by shaolin on 2017/4/12.
 */
public class HeResp {
    @JsonProperty("HeWeather5")
    private List<HeRespItem> HeWeather5;

    public List<HeRespItem> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeRespItem> heWeather5) {
        HeWeather5 = heWeather5;
    }
}
