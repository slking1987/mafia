package com.mafia.inter.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Preconditions;
import com.mafia.core.config.UrlConfig;
import com.mafia.core.constant.AqiLevel;
import com.mafia.core.constant.RespCode;
import com.mafia.core.exception.MafiaInterfaceException;
import com.mafia.core.log.LogService;
import com.mafia.core.util.DateUtil;
import com.mafia.core.util.HttpUtil;
import com.mafia.core.util.JsonUtil;
import com.mafia.inter.request.HeReq;
import com.mafia.inter.response.*;
import com.mafia.inter.vo.AqiVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by shaolin on 2017/4/6.
 */
@Service
public class AqiInterfaceService {

    private static final Class LOG_CLASS = AqiInterfaceService.class;

    private static final String METHOD_GET_WEATHER = "/v5/weather";
    private static final String SUCCESS_CODE = "ok";

    @Autowired
    private UrlConfig urlConfig;
    @Autowired
    private LogService logService;

    public AqiVO getApiItemByCity(String cityCode) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotEmpty(cityCode), "cityCode is empty");

        AqiVO vo = new AqiVO();
        String url = urlConfig.getHe_aqi() + METHOD_GET_WEATHER;
        HeReq req = new HeReq();
        req.setKey(urlConfig.getHe_aqi_key());
        req.setCity(cityCode);

        String respStr = HttpUtil.httpGetRequest(url, req);
        if(StringUtils.isEmpty(respStr)) {
            throw new MafiaInterfaceException(RespCode.INTERFACE_COMMON_ERROR_RESP_EMPTY);
        }
        HeResp resp = JsonUtil.parse(respStr, HeResp.class);
        if(resp == null || resp.getHeWeather5() == null || resp.getHeWeather5().isEmpty()) {
            throw new MafiaInterfaceException(RespCode.INTERFACE_COMMON_ERROR_RESP_FORMAT);
        }
        HeRespItem respItem = resp.getHeWeather5().get(0);
        if(StringUtils.isEmpty(respItem.getStatus()) || !SUCCESS_CODE.equals(respItem.getStatus())) {
            throw new MafiaInterfaceException(RespCode.INTERFACE_COMMON_FAILED);
        } else if(respItem.getAqi() == null || respItem.getAqi().getCity() == null) {
            logService.info(LOG_CLASS, "no aqi info for " + cityCode);
        } else {
            HeAqiItem aqi = respItem.getAqi().getCity();
            HeBasic basic = respItem.getBasic();
            vo.setAqi(aqi.getAqi());
            vo.setPm25(aqi.getPm25());
            vo.setCityId(basic.getId());
            vo.setCityDesc(basic.getCity());
            vo.setAqiLevel(AqiLevel.getByDesc(aqi.getQlty()));
            vo.setTime(DateUtil.str2Date(basic.getUpdate().getLoc() + ":00"));
        }

        return vo;
    }
}
