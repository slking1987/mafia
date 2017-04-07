package com.mafia.api.util;

import com.google.common.base.Preconditions;
import com.mafia.api.request.ApiReq;
import com.mafia.api.request.ApiReqHeader;
import com.mafia.api.response.ApiResp;
import com.mafia.api.response.ApiRespHeader;
import com.mafia.core.constant.RespCode;
import com.mafia.core.exception.MafiaApiException;
import com.mafia.core.exception.MafiaException;
import com.mafia.core.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.mafia.core.constant.RespCode.API_COMMON_ERROR_PARAM;

public abstract class ApiUtil {

    public static ApiReq checkParam(String encodeReq) throws Exception
    {
        if(StringUtils.isEmpty(encodeReq))
        {
            throw new MafiaApiException(API_COMMON_ERROR_PARAM, "param data not exist");
        }

        ApiReq apiReq = getApiReq(encodeReq);
        Preconditions.checkNotNull(apiReq, "ApiReq is null");
        ApiUtil.checkParamByValidator(apiReq);
        ApiUtil.checkParamByValidator(apiReq.getHeader());

        return apiReq;
    }

    public static <E> void checkParamByValidator(List<E> list) throws Exception
    {
        if(list != null)
        {
            for(E obj : list)
            {
                checkParamByValidator(obj);
            }
        }
    }

    public static <E> void checkParamByValidator(E obj) throws Exception
    {
        ValidatorUtil.checkParam(obj, MafiaApiException.class, API_COMMON_ERROR_PARAM.getCode());
    }

    public static String getIpAddr(HttpServletRequest request)
    {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static boolean hasIPMatch(String clientAddr, List<String> ipExps)
    {
        for(String ip : ipExps)
        {
            //如果是内网IP地址，匹配前两个段
            if (isInterIp(clientAddr, ip)) return true;
            //IP段匹配 中间使用"-"分割
            if (isRangeIp(clientAddr, ip)) return true;
            if (clientAddr.matches(ip)) return true;
        }
        return false;
    }

    public static ApiResp genApiResp(RespCode respCode, Object body, Object... respMsgArgs)
    {
        Integer code = respCode.getCode();
        String desc = I18nUtil.getMsg(respCode.getMsgCode(), respMsgArgs);
        return ApiUtil.genApiResp(code, desc, body);
    }

    public static ApiResp genApiResp(MafiaException e)
    {
        return ApiUtil.genApiResp(e.getCode(), e.getMessage(), ExceptionUtil.getStackTraceShortInfo(e));
    }

    public static ApiResp genApiResp(RespCode respCode, Exception e)
    {
        return ApiUtil.genApiResp(respCode.getCode(), e.getMessage(), ExceptionUtil.getStackTraceInfo(e));
    }

    public static ApiReq genApiReq(String appId, String key, String method, Object body)
    {
        String bodyStr = JsonUtil.writeValueQuite(body);

        ApiReqHeader apiReqHeader = new ApiReqHeader();
        apiReqHeader.setAppId(appId);
        apiReqHeader.setMethod(method);
        apiReqHeader.setOperatorId(StringUtils.EMPTY);
        apiReqHeader.setOperatorName(StringUtils.EMPTY);
        apiReqHeader.setSign(ApiSecurityUtil.genSign(appId, bodyStr, key));

        ApiReq apiReq = new ApiReq();
        apiReq.setHeader(apiReqHeader);
        apiReq.setBody(bodyStr);

        return apiReq;
    }

    private static ApiResp genApiResp(Integer code, String desc, Object body)
    {
        ApiResp apiResp = new ApiResp();
        ApiRespHeader apiRespHeader = new ApiRespHeader();
        apiRespHeader.setCode(code);
        apiRespHeader.setDesc(desc);
        apiResp.setHeader(apiRespHeader);
        apiResp.setBody(body);
        return apiResp;
    }

    private static boolean isRangeIp(String checkIp, String ipRange)
    {
        if(StringUtils.isEmpty(checkIp) || StringUtils.isEmpty(ipRange))
        {
            return false;
        }
        if(ipRange.indexOf("-") == 0)
        {
            return false;
        }

        String[] ipRangeArr = ipRange.split("-");
        if(ipRangeArr == null || ipRangeArr.length != 2)
        {
            return false;
        }
        String ipRangeFrom = ipRangeArr[0];
        String ipRangeTo = ipRangeArr[1];

        String[] checkIpArr = checkIp.split("\\.");
        String[] ipRangeFromArr = ipRangeFrom.split("\\.");
        String[] ipRangeToArr = ipRangeTo.split("\\.");

        for(int i = 0; i < 4; i ++)
        {
            int tempIp = Integer.valueOf(checkIpArr[i]);
            int tempIpRangeFrom = Integer.valueOf(ipRangeFromArr[i]);
            int tempIpRangeTo = Integer.valueOf(ipRangeToArr[i]);
            if(!MathUtil.isIntInRange(tempIp, tempIpRangeFrom, tempIpRangeTo))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isInterIp(String clientIP, String allowIP) {
        boolean isAllowIP = false;
        String[] interIPs = new String[] {"10", "172", "192"};// 内网IP地址开始段
        String[] clientIPs = clientIP.split("\\.");// 以点号分割客户端IP地址为数组
        if(clientIPs == null || clientIPs.length == 0) return isAllowIP;
        String startClientIP = clientIPs[0];// 获取客户端IP地址的第一位

        boolean isInterIP = Arrays.asList(interIPs).contains(startClientIP);// 判断客户端地址是否属于内网地址
        if(isInterIP) {
            String[] ip = allowIP.split("\\.");
            String regex = ip[0] + "." + ip[1];
            if(clientIP.matches("^("+regex+")(.*)$")) {
                isAllowIP = true;
            } else {
                isAllowIP = false;
            }
        }
        return isAllowIP;
    }

    private static ApiReq getApiReq(String encodeReq) throws IOException {
        String decodeReq = new String(Base64Utils.decodeFromString(encodeReq));
        ApiReq apiReq = JsonUtil.parse(decodeReq, ApiReq.class);
        return apiReq;
    }
}
