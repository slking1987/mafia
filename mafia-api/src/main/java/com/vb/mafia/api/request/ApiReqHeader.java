package com.vb.mafia.api.request;

import org.hibernate.validator.constraints.NotEmpty;

public class ApiReqHeader {
    @NotEmpty(message = "[ApiReqHeader] appId is empty")
    private String appId;
    private String operatorId;
    private String operatorName;
    @NotEmpty(message = "[ApiReqHeader] method is empty")
    private String method;
    @NotEmpty(message = "[ApiReqHeader] sign is empty")
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
