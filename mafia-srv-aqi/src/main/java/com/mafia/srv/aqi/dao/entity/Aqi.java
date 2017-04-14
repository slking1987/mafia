package com.mafia.srv.aqi.dao.entity;

import java.util.Date;

/**
 * Created by shaolin on 2017/4/12.
 */
public class Aqi {
    private String cityId;
    private String cityDesc;
    private Integer time;
    private Integer date;
    private Integer aqi;
    private Integer aqiLevel;
    private Date createTime;

    // for graph
    private String lon;
    private String lat;

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Integer getAqiLevel() {
        return aqiLevel;
    }

    public void setAqiLevel(Integer aqiLevel) {
        this.aqiLevel = aqiLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
