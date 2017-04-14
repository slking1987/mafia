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
    private String he_aqi;
    private String he_aqi_key;

    public String getHe_aqi() {
        return he_aqi;
    }

    public void setHe_aqi(String he_aqi) {
        this.he_aqi = he_aqi;
    }

    public String getHe_aqi_key() {
        return he_aqi_key;
    }

    public void setHe_aqi_key(String he_aqi_key) {
        this.he_aqi_key = he_aqi_key;
    }
}
