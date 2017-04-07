package com.mafia.core.config;

import com.mafia.core.constant.ConfigConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by shaolin on 2017/4/6.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = ConfigConstant.PREFIX_URL)
public class UrlConfig {
    private String aqi;
    private String aqi_key;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAqi_key() {
        return aqi_key;
    }

    public void setAqi_key(String aqi_key) {
        this.aqi_key = aqi_key;
    }
}
