package com.mafia.srv.aqi.dao.entity;

import java.util.Date;

/**
 * Created by shaolin on 2017/4/6.
 */
public class AirQualityIndex {
    private String geoCity;
    private Integer time;
    private Integer aqi;
    private Integer date;
    private Date createTime;

    public String getGeoCity() {
        return geoCity;
    }

    public void setGeoCity(String geoCity) {
        this.geoCity = geoCity;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
