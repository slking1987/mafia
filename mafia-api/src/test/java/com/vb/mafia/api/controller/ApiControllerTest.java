package com.vb.mafia.api.controller;

import com.vb.mafia.api.request.ApiReq;
import com.vb.mafia.api.request.demo.DemoGetListReq;
import com.vb.mafia.api.request.demo.DemoInsertReq;
import com.vb.mafia.api.util.ApiConstant;
import com.vb.mafia.api.util.ApiUtil;
import com.vb.mafia.core.constant.ModuleType;
import com.vb.mafia.core.util.HttpUtil;
import com.vb.mafia.core.util.JsonUtil;
import org.junit.Test;
import org.springframework.util.Base64Utils;

/**
 * Created by shaolin on 2017/1/18.
 */
public class ApiControllerTest {

    private static final String LOCAL_URL = "http://localhost:8080/api";

    @Test
    public void testDemoGetList()
    {
        DemoGetListReq req = new DemoGetListReq();
        req.setName("test");
        req.setModuleType(ModuleType.DEFAULT.name());
        this.post(ApiConstant.METHOD_DEMO_GET_LIST, req);
    }

    @Test
    public void testDemoInsert()
    {
        DemoInsertReq req = new DemoInsertReq();
        req.setName("test insert..");
        this.post(ApiConstant.METHOD_DEMO_INSERT, req);
    }

    private void post(String method, Object req)
    {
        ApiReq apiReq = ApiUtil.genApiReq(ApiConstant.APP_ID_DEMO, ApiConstant.APP_KEY_DEMO, method, req);
        String apiReqStr = JsonUtil.writeValueQuite(apiReq);
        String encodeApiReq = Base64Utils.encodeToString(apiReqStr.getBytes());

        HttpUtil.httpPostRequest(LOCAL_URL, new X5Req(encodeApiReq));
    }

    private class X5Req
    {
        private String data;

        X5Req(String data)
        {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}