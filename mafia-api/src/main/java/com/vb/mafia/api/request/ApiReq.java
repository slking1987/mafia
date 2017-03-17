package com.vb.mafia.api.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ApiReq {
    @NotNull(message = "[ApiReq] header is null")
    private ApiReqHeader header;
    @NotEmpty(message = "[ApiReq] body is empty")
    private String body;

    public ApiReqHeader getHeader() {
        return header;
    }

    public void setHeader(ApiReqHeader header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
