package com.mafia.api.service;

import com.mafia.srv.aqi.service.AqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shaolin on 2017/4/6.
 */
@Service
public class AqiApiService {
    @Autowired
    private AqiService aqiService;

    public void refresh(String bodyStr) throws Exception {
        aqiService.refreshAqiHe();
    }
}
