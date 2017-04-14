package com.mafia.srv.aqi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mafia.core.constant.AqiLevel;
import com.mafia.core.constant.ModuleType;
import com.mafia.core.log.LogService;
import com.mafia.core.util.CollectionUtil;
import com.mafia.core.util.DateUtil;
import com.mafia.core.util.JsonUtil;
import com.mafia.inter.service.AqiInterfaceService;
import com.mafia.inter.vo.AqiVO;
import com.mafia.srv.aqi.dao.entity.Aqi;
import com.mafia.srv.aqi.dao.entity.City;
import com.mafia.srv.aqi.dao.master.AqiMapper;
import com.mafia.srv.aqi.dao.master.CityMapper;
import com.mafia.srv.aqi.vo.AqiGraphItem;
import com.mafia.srv.aqi.vo.AqiGraphVO;
import com.mafia.srv.aqi.vo.AqiSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by shaolin on 2017/4/6.
 */
@Service
public class AqiService {

    private static final Class LOG_CLASS = AqiService.class;

    @Autowired
    private AqiMapper aqiMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AqiInterfaceService aqiInterSrv;
    @Autowired
    private LogService logService;

    public void refreshAqiHe() throws Exception {
        List<City> cityList = cityMapper.selectList();
        List<Aqi> aqiList = CollectionUtil.newArrayList();
        Date createTime = new Date();
        cityList.forEach(item -> {
            try {
                AqiVO vo = aqiInterSrv.getApiItemByCity(item.getId());
                Aqi aqi = new Aqi();
                aqi.setCityId(vo.getCityId());
                aqi.setCityDesc(vo.getCityDesc());
                aqi.setAqi(vo.getAqi());
                aqi.setAqiLevel(vo.getAqiLevel().getCode());
                aqi.setTime(DateUtil.date2Int(vo.getTime()));
                int[] fullDate = DateUtil.getFullDate(vo.getTime());
                aqi.setDate(fullDate[0]);
                aqi.setCreateTime(createTime);
                aqiList.add(aqi);
            } catch (Exception e) {
                logService.error(LOG_CLASS, e);
            }
        });

        this.insertAqiBatch(aqiList, 100);
    }

    public void refreshCity() throws Exception {
        String filePath = "city.json";
        List<City> cityList = JsonUtil.getObjectMapper().readValue(new File(filePath), new TypeReference<List<City>>() {
        });
        cityList.forEach(item -> {
            if (item.getCityEn().equals(item.getLeaderEn())) {
                item.setLevel(3);
            } else {
                item.setLevel(4);
            }
        });

        cityMapper.insertBatch(cityList);
    }

    /**
     * AqiGraphItem eg. '海门':[121.15,31.89,90]
     * @return
     */
    public AqiGraphVO getGraphData() {
        AqiGraphVO vo = new AqiGraphVO();

        Date maxCreateTime = aqiMapper.getMaxCreateTime();

        AqiSearchVO searchVO = new AqiSearchVO();
        // searchVO.setCityLevel(3);
        searchVO.setCreateTime(maxCreateTime);
        List<Aqi> aqiList = aqiMapper.searchList(searchVO);
        Map<Integer, List<Aqi>> aqiLevelMap = aqiList.stream().collect((Collectors.groupingBy(Aqi::getAqiLevel)));

        List<AqiGraphItem> goodList = this.genAqiGraphItemList(aqiLevelMap.get(AqiLevel.GOOD.getCode()));
        List<AqiGraphItem> moderateList = this.genAqiGraphItemList(aqiLevelMap.get(AqiLevel.MODERATE.getCode()));
        List<AqiGraphItem> lightlyPollutedList = this.genAqiGraphItemList(aqiLevelMap.get(AqiLevel.LIGHTLY_POLLUTED.getCode()));
        List<AqiGraphItem> moderatePollutedList = this.genAqiGraphItemList(aqiLevelMap.get(AqiLevel.MODERATELY_POLLUTED.getCode()));
        List<AqiGraphItem> heavilyPollutedList = this.genAqiGraphItemList(aqiLevelMap.get(AqiLevel.HEAVILY_POLLUTED.getCode()));
        List<AqiGraphItem> severelyPollutedList = this.genAqiGraphItemList(aqiLevelMap.get(AqiLevel.SEVERELY_POLLUTED.getCode()));
        List<AqiGraphItem> fullList = CollectionUtil.newArrayList();
        fullList.addAll(goodList);
        fullList.addAll(moderateList);
        fullList.addAll(lightlyPollutedList);
        fullList.addAll(moderatePollutedList);
        fullList.addAll(heavilyPollutedList);
        fullList.addAll(severelyPollutedList);
        List<AqiGraphItem> top5List = this.genAqiGraphItemList(aqiList.subList(0, aqiList.size() < 5 ? aqiList.size() : 5));

        vo.setFullList(fullList);
        vo.setTopList(top5List);
        vo.setGoodList(goodList);
        vo.setModerateList(moderateList);
        vo.setLightlyPollutedList(lightlyPollutedList);
        vo.setModeratePollutedList(moderatePollutedList);
        vo.setHeavilyPollutedList(heavilyPollutedList);
        vo.setSeverelyPollutedList(severelyPollutedList);
        vo.setMapId("china");
        vo.setUpdateTime(DateUtil.date2str(maxCreateTime));

        return vo;
    }

    private List<AqiGraphItem> genAqiGraphItemList(List<Aqi> list) {
        return list == null ? CollectionUtil.newArrayList() : list.stream().map(item -> genAqiGraphItem(item)).collect(Collectors.toList());
    }

    private AqiGraphItem genAqiGraphItem(Aqi aqi) {
        AqiGraphItem item = new AqiGraphItem();
        item.setName(aqi.getCityDesc());
        item.setValue(new Double[]{
                Double.valueOf(aqi.getLon()),
                Double.valueOf(aqi.getLat()),
                (double) aqi.getAqi()
        });
        return item;
    }

    private void insertAqiBatch(List<Aqi> list, int splitCount) {
        int size = list.size();
        int remain = size % splitCount;
        int divide = size / splitCount;
        int syncTimes = remain == 0 ? divide : divide + 1;
        for (int j = 0; j < syncTimes; j++) {
            int fromIndex = splitCount * j;
            int endIndex = splitCount * (j + 1) > size ? size : splitCount * (j + 1);
            List<Aqi> tempList = list.subList(fromIndex, endIndex);
            logService.info(LOG_CLASS, ModuleType.SRV_AQI, String.format("insert aqi batch fromIndex[%s] endIndex[%s]", fromIndex, endIndex));
            aqiMapper.insertBatch(tempList);
        }
    }
}
