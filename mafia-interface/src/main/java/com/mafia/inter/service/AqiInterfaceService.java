package com.mafia.inter.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.mafia.core.config.UrlConfig;
import com.mafia.core.util.DateUtil;
import com.mafia.core.util.HttpUtil;
import com.mafia.core.util.JsonUtil;
import com.mafia.inter.request.AqiReq;
import com.mafia.inter.response.AqiItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by shaolin on 2017/4/6.
 */
@Service
public class AqiInterfaceService {

    private static final String METHOD_GET_ALL = "/querys/aqi_ranking.json";

    @Autowired
    private UrlConfig urlConfig;

    public List<AqiItem> getAllCityAqiInfo() throws Exception {
        String url = urlConfig.getAqi() + METHOD_GET_ALL;
        AqiReq aqiReq = new AqiReq();
        aqiReq.setToken(urlConfig.getAqi_key());

        String respStr = HttpUtil.httpGetRequest(url, aqiReq);
        if(StringUtils.isEmpty(respStr)) {
            throw new Exception("resp empty");
        }
        JsonNode node = JsonUtil.getObjectMapper().readTree(respStr);
        JsonNode errorNode = node.findPath("error");
        if(errorNode != null) {
            throw new Exception("resp error:" + errorNode.asText());
        }

        List<AqiItem> list = JsonUtil.parse(respStr, new TypeReference<List<AqiItem>>() {});
        list.forEach(item -> {
            String timePoint = item.getTime_point();// 2017-04-06T17:00:00Z
            if(StringUtils.isNotEmpty(timePoint)) {
                String[] temp = timePoint.split("T");
                Date date = DateUtil.str2Date(temp[0] + " " + temp[1].substring(0, temp[1].length() -1));
                item.setTime(date);
            }
        });
        return list;
    }
}
