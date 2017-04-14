package com.mafia.srv.aqi.dao.master;

import com.mafia.srv.aqi.dao.entity.City;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by shaolin on 2017/4/10.
 */
public interface CityMapper {
    void insertBatch(List<City> items);

    @Select("SELECT * FROM city WHERE is_aqi=1")
    List<City> selectList();
}
