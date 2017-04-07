package com.mafia.api.util;

import com.mafia.core.util.CollectionUtil;

import java.util.List;

public abstract class ApiConstant {

    public static final String APP_ID_DEMO = "app_id_demo";
    public static final String APP_KEY_DEMO = "app_key_demo";

    public static final String METHOD_DEMO_GET_LIST = "demo.getList";
    public static final String METHOD_DEMO_INSERT = "demo.insert";

    public static final String METHOD_AQI_REFRESH = "aqi.refresh";

    public static final List<String> ALLOW_METHOD_LIST_DEMO = CollectionUtil.newArrayList();
    static
    {
        ALLOW_METHOD_LIST_DEMO.add(METHOD_DEMO_GET_LIST);
        ALLOW_METHOD_LIST_DEMO.add(METHOD_DEMO_INSERT);
        ALLOW_METHOD_LIST_DEMO.add(METHOD_AQI_REFRESH);
    }

    public static final List<String> NOT_CHECK_IP_METHOD_LIST = CollectionUtil.newArrayList();
    static
    {
        NOT_CHECK_IP_METHOD_LIST.add(METHOD_DEMO_GET_LIST);
    }

    public static final List<String> ALLOW_IP_LIST_DEMO = CollectionUtil.newArrayList();
    static
    {
        ALLOW_IP_LIST_DEMO.add("127.0.0.1");
    }
}
