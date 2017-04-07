package com.mafia.api.response;

public class ApiResp {
    private ApiRespHeader header;
    private Object body;

    public ApiRespHeader getHeader() {
        return header;
    }

    public void setHeader(ApiRespHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
