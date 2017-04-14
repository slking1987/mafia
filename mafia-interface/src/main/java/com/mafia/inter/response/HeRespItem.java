package com.mafia.inter.response;

/**
 * Created by shaolin on 2017/4/12.
 */
public class HeRespItem {
    private String status;
    private HeAqi aqi;
    private HeBasic basic;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HeAqi getAqi() {
        return aqi;
    }

    public void setAqi(HeAqi aqi) {
        this.aqi = aqi;
    }

    public HeBasic getBasic() {
        return basic;
    }

    public void setBasic(HeBasic basic) {
        this.basic = basic;
    }
}
