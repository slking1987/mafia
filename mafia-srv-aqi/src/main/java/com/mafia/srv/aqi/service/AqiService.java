package com.mafia.srv.aqi.service;

import com.mafia.core.constant.ModuleType;
import com.mafia.core.log.LogService;
import com.mafia.core.util.CollectionUtil;
import com.mafia.core.util.DateUtil;
import com.mafia.inter.response.AqiItem;
import com.mafia.inter.service.AqiInterfaceService;
import com.mafia.srv.aqi.dao.entity.AirQualityIndex;
import com.mafia.srv.aqi.dao.master.AirQualityIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shaolin on 2017/4/6.
 */
@Service
public class AqiService {

    private static final Class LOG_CLASS = AqiService.class;

    @Autowired
    private AirQualityIndexMapper aqiMapper;
    @Autowired
    private AqiInterfaceService aqiInterSrv;
    @Autowired
    private LogService logService;

    public void refreshAqi() throws Exception {
        List<AqiItem> list = aqiInterSrv.getAllCityAqiInfo();
        List<AirQualityIndex> aqiList = CollectionUtil.newArrayList();
        list.forEach(item -> {
            AirQualityIndex aqi = new AirQualityIndex();
            aqi.setGeoCity(item.getArea());
            aqi.setTime(DateUtil.date2Int(item.getTime()));
            aqi.setAqi(item.getAqi());
            int[] fullDate = DateUtil.getFullDate(item.getTime());
            aqi.setDate(fullDate[0]);
            aqiList.add(aqi);
        });

        this.insertAlertBatch(aqiList, 100);
    }

    private void insertAlertBatch(List<AirQualityIndex> list, int splitCount) {
        int size = list.size();
        int remain = size % splitCount;
        int divide = size / splitCount;
        int syncTimes = remain == 0 ? divide : divide + 1;
        for(int j = 0; j < syncTimes; j ++) {
            int fromIndex = splitCount * j;
            int endIndex = splitCount * (j + 1) > size ? size : splitCount * (j + 1);
            List<AirQualityIndex> tempList = list.subList(fromIndex, endIndex);
            logService.info(LOG_CLASS, ModuleType.SRV_AQI, String.format("insert alert batch fromIndex[%s] endIndex[%s]", fromIndex, endIndex));
            aqiMapper.insertBatch(tempList);
        }
    }
}
