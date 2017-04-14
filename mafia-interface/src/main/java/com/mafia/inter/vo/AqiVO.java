package com.mafia.inter.vo;

import com.mafia.core.constant.AqiLevel;

import java.util.Date;

/**
 * Created by shaolin on 2017/4/12.
 */
public class AqiVO {
    private String cityId;
    private String cityDesc;
    private Integer aqi;
    private AqiLevel aqiLevel;
    private Date time;
    private Integer pm25;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public AqiLevel getAqiLevel() {
        return aqiLevel;
    }

    public void setAqiLevel(AqiLevel aqiLevel) {
        this.aqiLevel = aqiLevel;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }
}
