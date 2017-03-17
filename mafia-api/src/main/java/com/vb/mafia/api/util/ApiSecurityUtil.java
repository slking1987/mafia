package com.vb.mafia.api.util;

import com.vb.mafia.api.request.ApiReq;
import com.vb.mafia.api.request.ApiReqHeader;
import com.vb.mafia.core.exception.MafiaApiException;
import com.vb.mafia.core.util.MafiaUtil;
import com.vb.mafia.core.util.Md5Util;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.vb.mafia.core.constant.RespCode.*;

public abstract class ApiSecurityUtil {

    public static void check(HttpServletRequest httpServletReq, ApiReq apiReq) throws Exception
    {
        String appId = apiReq.getHeader().getAppId();
        String method = apiReq.getHeader().getMethod();

        ApiSecurityConfig config = ApiSecurityUtil.getSecurityConfigByAppId(appId);
        List<String> ipList = config.getIpList();
        List<String> methodList = config.getMethodList();
        String key = getAppKey(config);

        String ip = ApiUtil.getIpAddr(httpServletReq);
        checkIp(ip, ipList, method);

        checkMethod(method, methodList);

        checkSign(apiReq, key);
    }

    public static String genSign(String appId, String body, String key)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(appId);
        sb.append(body);
        sb.append(key);
        return Md5Util.getMD5(sb.toString()).toUpperCase();
    }

    private static void checkSign(ApiReq apiReq, String key) throws Exception
    {
        ApiReqHeader header = apiReq.getHeader();

        String signComp = ApiSecurityUtil.genSign(header.getAppId(), apiReq.getBody(), key);
        String sign = header.getSign();
        if(!StringUtils.equals(sign, signComp))
        {
            throw new MafiaApiException(API_COMMON_ERROR_CHECK_SIGN);
        }
    }

    private static void checkIp(String ip, List<String> ipList, String method) throws Exception
    {
        if(ApiConstant.NOT_CHECK_IP_METHOD_LIST.contains(method))
        {
            return;
        }
        else
        {
            if(MafiaUtil.isOnlineMode() && !ApiUtil.hasIPMatch(ip, ipList))
            {
                throw new MafiaApiException(API_COMMON_ERROR_IP_ILLEGAL);
            }
        }
    }

    private static void checkMethod(String method, List<String> methodList) throws Exception
    {
        if(!methodList.contains(method))
        {
            throw new MafiaApiException(API_COMMON_ERROR_METHOD_ILLEGAL);
        }
    }

    private static ApiSecurityConfig getSecurityConfigByAppId(String appId) throws MafiaApiException
    {
        ApiSecurityConfig config = ApiSecurityConfig.getByAppId(appId);
        if(config == null)
        {
            throw new MafiaApiException(API_COMMON_ERROR_SEC_CONF_NOT_FOUND);
        }
        return config;
    }

    private static String getAppKey(ApiSecurityConfig config)
    {
        return config.getAppKey();
    }
}
