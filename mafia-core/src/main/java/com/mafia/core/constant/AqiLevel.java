package com.mafia.core.constant;

import com.mafia.core.util.CollectionUtil;

import java.util.Map;

/**
 * Created by shaolin on 2017/4/12.
 */
public enum AqiLevel {
    GOOD(1, "优"),// 0-50 绿色
    MODERATE(2, "良"),// 51-100 黄色
    LIGHTLY_POLLUTED(3, "轻度污染"),// 101-150 橙色
    MODERATELY_POLLUTED(4, "中度污染"),// 151-200 红色
    HEAVILY_POLLUTED(5, "重度污染"),// 201-300 紫色
    SEVERELY_POLLUTED(6, "严重污染")// >300 褐红色
    ;

    private Integer code;
    private String desc;

    AqiLevel(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, AqiLevel> codeMap = CollectionUtil.newHashMap();
    private static final Map<String, AqiLevel> descMap = CollectionUtil.newHashMap();
    static {
        for (AqiLevel aqiLevel : AqiLevel.values()) {
            codeMap.put(aqiLevel.getCode(), aqiLevel);
            descMap.put(aqiLevel.getDesc(), aqiLevel);
        }
    }

    public static AqiLevel getByCode(Integer code) {
        return codeMap.get(code);
    }

    public static AqiLevel getByDesc(String desc) {
        return descMap.get(desc);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
