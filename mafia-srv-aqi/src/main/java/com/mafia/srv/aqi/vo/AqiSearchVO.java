package com.mafia.srv.aqi.vo;

import java.util.Date;

/**
 * Created by shaolin on 2017/4/12.
 */
public class AqiSearchVO {
    private Integer cityLevel;
    private Integer date;
    private Date createTime;

    public Integer getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(Integer cityLevel) {
        this.cityLevel = cityLevel;
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
