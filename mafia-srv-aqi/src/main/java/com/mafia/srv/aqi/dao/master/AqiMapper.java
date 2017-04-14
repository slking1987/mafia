package com.mafia.srv.aqi.dao.master;

import com.mafia.srv.aqi.dao.entity.Aqi;
import com.mafia.srv.aqi.vo.AqiSearchVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by shaolin on 2017/4/12.
 */
public interface AqiMapper {
    void insertBatch(List<Aqi> items);

    List<Aqi> searchList(@Param("searchVO") AqiSearchVO searchVO);

    @Select("SELECT max(create_time) FROM aqi")
    Date getMaxCreateTime();
}
