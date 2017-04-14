package com.mafia.portal.controller;

import com.mafia.core.log.LogService;
import com.mafia.srv.aqi.service.AqiService;
import com.mafia.srv.aqi.vo.AqiGraphVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shaolin on 2017/4/7.
 */
@Controller
@RequestMapping("/aqi")
public class AqiController {

    private static final Class LOG_CLASS = AqiController.class;

    @Autowired
    private AqiService aqiService;
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/getGraphData", method = RequestMethod.GET)
    @ResponseBody
    public AqiGraphVO getGraphData() {
        logService.debug(LOG_CLASS, "[aqi] get graph data");
        return aqiService.getGraphData();
    }
}
