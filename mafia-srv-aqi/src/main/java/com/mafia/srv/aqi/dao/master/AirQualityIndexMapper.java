package com.mafia.srv.aqi.dao.master;

import com.mafia.srv.aqi.dao.entity.AirQualityIndex;

import java.util.List;

/**
 * Created by shaolin on 2017/4/6.
 */
public interface AirQualityIndexMapper {
    void insertBatch(List<AirQualityIndex> items);
}
