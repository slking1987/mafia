package com.mafia.api.util;

import com.mafia.core.util.CollectionUtil;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public enum ApiSecurityConfig {

    DEMO(ApiConstant.APP_ID_DEMO, ApiConstant.APP_KEY_DEMO, ApiConstant.ALLOW_METHOD_LIST_DEMO, ApiConstant.ALLOW_IP_LIST_DEMO);

    private String appId;
    private String appKey;
    private List<String> methodList;
    private List<String> ipList;

    private static final Map<String, ApiSecurityConfig> lookup = CollectionUtil.newHashMap();
    static
    {
        for(ApiSecurityConfig s : EnumSet.allOf(ApiSecurityConfig.class))
        {
            lookup.put(s.appId, s);
        }
    }

    ApiSecurityConfig(String appId, String appKey, List<String> methodList, List<String> ipList)
    {
        this.appId = appId;
        this.appKey = appKey;
        this.methodList = methodList;
        this.ipList = ipList;
    }

    public static ApiSecurityConfig getByAppId(String appId)
    {
        return lookup.get(appId);
    }

    public String getAppKey()
    {
        return this.appKey;
    }
    public List<String> getMethodList()
    {
        return this.methodList;
    }
    public List<String> getIpList()
    {
        return this.ipList;
    }
}
