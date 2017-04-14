package com.mafia.srv.aqi.vo;

import java.util.List;

/**
 * Created by shaolin on 2017/4/10.
 */
public class AqiGraphVO {
    private List<AqiGraphItem> fullList;
    private List<AqiGraphItem> topList;
    private List<AqiGraphItem> goodList;
    private List<AqiGraphItem> moderateList;
    private List<AqiGraphItem> lightlyPollutedList;
    private List<AqiGraphItem> moderatePollutedList;
    private List<AqiGraphItem> heavilyPollutedList;
    private List<AqiGraphItem> severelyPollutedList;

    private String updateTime;
    private String mapId;

    public List<AqiGraphItem> getFullList() {
        return fullList;
    }

    public void setFullList(List<AqiGraphItem> fullList) {
        this.fullList = fullList;
    }

    public List<AqiGraphItem> getTopList() {
        return topList;
    }

    public void setTopList(List<AqiGraphItem> topList) {
        this.topList = topList;
    }

    public List<AqiGraphItem> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<AqiGraphItem> goodList) {
        this.goodList = goodList;
    }

    public List<AqiGraphItem> getModerateList() {
        return moderateList;
    }

    public void setModerateList(List<AqiGraphItem> moderateList) {
        this.moderateList = moderateList;
    }

    public List<AqiGraphItem> getLightlyPollutedList() {
        return lightlyPollutedList;
    }

    public void setLightlyPollutedList(List<AqiGraphItem> lightlyPollutedList) {
        this.lightlyPollutedList = lightlyPollutedList;
    }

    public List<AqiGraphItem> getModeratePollutedList() {
        return moderatePollutedList;
    }

    public void setModeratePollutedList(List<AqiGraphItem> moderatePollutedList) {
        this.moderatePollutedList = moderatePollutedList;
    }

    public List<AqiGraphItem> getHeavilyPollutedList() {
        return heavilyPollutedList;
    }

    public void setHeavilyPollutedList(List<AqiGraphItem> heavilyPollutedList) {
        this.heavilyPollutedList = heavilyPollutedList;
    }

    public List<AqiGraphItem> getSeverelyPollutedList() {
        return severelyPollutedList;
    }

    public void setSeverelyPollutedList(List<AqiGraphItem> severelyPollutedList) {
        this.severelyPollutedList = severelyPollutedList;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
}
