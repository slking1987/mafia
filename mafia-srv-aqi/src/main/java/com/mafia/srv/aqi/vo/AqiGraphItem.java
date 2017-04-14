package com.mafia.srv.aqi.vo;

/**
 * Created by shaolin on 2017/4/10.
 */
public class AqiGraphItem {
    private String name;
    private Double[] value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getValue() {
        return value;
    }

    public void setValue(Double[] value) {
        this.value = value;
    }
}
