package com.mafia.core.constant;

public enum RespCode {

    API_COMMON_SUCCESS(200, "api.common.success", "成功"),

    // 1000-1999 API
    // 1000-1099 API COMMON
    API_COMMON_ERROR_PARAM(1001, "api.common.error.param", "参数错误"),
    API_COMMON_ERROR_CHECK_SIGN(1002, "api.common.error.checkSign", "签名校验失败"),
    API_COMMON_ERROR_METHOD_ILLEGAL(1003, "api.common.error.methodIllegal", "请求方法不合法"),
    API_COMMON_ERROR_IP_ILLEGAL(1004, "api.common.error.ipIllegal", "请求IP不合法"),
    API_COMMON_ERROR_HTTP_GET_FORBID(1005, "api.common.error.httpGetForbid", "HTTP GET请求屏蔽"),
    API_COMMON_ERROR_SEC_CONF_NOT_FOUND(1006, "api.common.error.secConfNotFound", "安全配置项不存在"),

    // 9000-9999 EXCEPTION
    EXP_IO(9001, "exp.io", "io exception"),
    EXP_RUNTIME(9002, "exp.runtime", "runtime exception"),
    EXP_DATABASE(9003, "exp.database", "database exception"),
    EXP_ILLEGAL_ARGUMENT(9004, "exp.IllegalArgument", "illegal argument exception"),
    EXP_MAFIA(9998, "exp.mafia", "mafia exception"),
    EXP_OTHERS(9999, "exp.others", "others exception")
    ;

    private Integer code;
    private String msgCode;
    private String desc;
    RespCode(Integer code, String msgCode, String desc)
    {
        this.code = code;
        this.msgCode = msgCode;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsgCode() {
        return msgCode;
    }
}
