package com.mafia.srv.aqi.util;

import com.mafia.core.util.CollectionUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by shaolin on 2017/4/18.
 */
public enum GraphMap {
    CHINA("china", "全国", StringUtils.EMPTY),
    BEIJING("beijing", "北京", "beijing"),
    HEBEI("hebei", "河北", "hebei"),
    SHANDONG("shandong", "山东", "shandong"),
    SHANXI("shanxi", "山西", "shanxi"),
    HEILONGJIANG("heilongjiang", "黑龙江", "heilongjiang"),
    SHANXI1("shan-xi", "陕西", "shan-xi"),
    NEIMENGGU("neimenggu", "内蒙古", "neimenggu"),
    GUANGDONG("guangdong", "广东", "guangdong"),
    FUJAIN("fujian", "福建", "fujian")
    ;
    private String code;
    private String desc;
    private String dbCode;
    GraphMap(String code, String desc, String dbCode) {
        this.code = code;
        this.desc = desc;
        this.dbCode = dbCode;
    }

    private static final Map<String, GraphMap> codeMap = CollectionUtil.newHashMap();
    private static final Map<String, GraphMap> descMap = CollectionUtil.newHashMap();
    static {
        for (GraphMap graphMap : GraphMap.values()) {
            codeMap.put(graphMap.getCode(), graphMap);
            descMap.put(graphMap.getDesc(), graphMap);
        }
    }

    public static GraphMap getByCode(String code) {
        return codeMap.get(code);
    }

    public static GraphMap getByDesc(String desc) {
        return descMap.get(desc);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode;
    }
}
