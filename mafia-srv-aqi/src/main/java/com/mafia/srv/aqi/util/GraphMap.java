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
    FUJAIN("fujian", "福建", "fujian"),
    ANHUI("anhui", "安徽", "anhui"),
    AOMEN("aomen", "澳门", "aomen"),
    CHONGQING("chongqing", "重庆", "chongqing"),
    GANSU("gansu", "甘肃", "gansu"),
    GUANGXI("guangxi", "广西", "guangxi"),
    GUIZHOU("guizhou", "贵州", "guizhou"),
    HAINAN("hainan", "海南", "hainan"),
    HENAN("henan", "河南", "henan"),
    HUBEI("hubei", "湖北", "hubei"),
    HUNAN("hunan", "湖南", "hunan"),
    JIANGSU("jiangsu", "江苏", "jiangsu"),
    JIANGXI("jiangxi", "江西", "jiangxi"),
    JILIN("jilin", "吉林", "jilin"),
    LIAONING("liaoning", "辽宁", "liaoning"),
    NINGXIA("ningxia", "宁夏", "ningxia"),
    QINGHAI("qinghai", "青海", "qinghai"),
    SHANGHAI("shanghai", "上海", "shanghai"),
    SICHUAN("sichuan", "四川", "sichuan"),
    TAIWAN("taiwan", "台湾", "taiwan"),
    TIANJIN("tianjin", "天津", "tianjin"),
    XIANGGANG("xianggang", "香港", "xianggang"),
    XINJIANG("xinjiang", "新疆", "xinjiang"),
    XIZANG("xizang", "西藏", "xizang"),
    YUNNAN("yunnan", "云南", "yunnan"),
    ZHEJIANG("zhejiang", "浙江", "zhejiang")
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
