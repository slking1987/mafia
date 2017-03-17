package com.vb.mafia.api.controller;

import com.vb.mafia.api.request.ApiReq;
import com.vb.mafia.api.response.ApiResp;
import com.vb.mafia.api.util.ApiSecurityUtil;
import com.vb.mafia.api.util.ApiUtil;
import com.vb.mafia.core.constant.ModuleType;
import com.vb.mafia.core.constant.RespCode;
import com.vb.mafia.core.exception.MafiaApiException;
import com.vb.mafia.core.log.LogService;
import com.vb.mafia.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
public class ApiController {

    private static final Class LOG_CLASS = ApiController.class;

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ApiResp get(HttpServletRequest request) throws MafiaApiException
    {
        // 屏蔽GET方法
        throw new MafiaApiException(RespCode.API_COMMON_ERROR_HTTP_GET_FORBID, "http get forbidden");
    }

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    public ApiResp post(HttpServletRequest request) throws Throwable
    {
        return this.process(request, this.genEncodeRequest(request));
    }

    private ApiResp process(HttpServletRequest request, String encodeRequest) throws Throwable
    {
        long startTime = System.currentTimeMillis();

        ApiReq apiReq = ApiUtil.checkParam(encodeRequest);

        ApiSecurityUtil.check(request, apiReq);

        String reqMethod = apiReq.getHeader().getMethod();
        String reqBody = apiReq.getBody();
        logService.info(LOG_CLASS, ModuleType.API_COMMON, reqMethod, reqBody);

        Object respBody = this.invokeMethod(reqMethod, reqBody);
        ApiResp apiResp = ApiUtil.genApiResp(RespCode.API_COMMON_SUCCESS, respBody, reqMethod);

        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        String logMsg = String.format("mafia api success, cost time:%sms, req:%s, resp:%s", costTime, apiReq.getBody(), JsonUtil.writeValueQuite(apiResp));
        logService.info(LOG_CLASS, ModuleType.DEFAULT, reqMethod, logMsg);
        return apiResp;
    }

    private Object invokeMethod(String reqMethod, String reqBody) throws Throwable {
        String[] args = reqMethod.split("\\.");
        String serviceNamePrefix = args[0];
        String methodName = args[1];
        String serviceName = serviceNamePrefix + "ApiService";

        Object serviceBean = appContext.getBean(serviceName);
        Method callMethod = serviceBean.getClass().getMethod(methodName, String.class);
        if(callMethod != null)
        {
            try
            {
                return callMethod.invoke(serviceBean, reqBody);
            }
            catch (InvocationTargetException e)
            {
                throw e.getTargetException();
            }
        }
        else
        {
            throw new MafiaApiException(RespCode.API_COMMON_ERROR_METHOD_ILLEGAL, String.format("request method not available, method[%s]", reqMethod));
        }
    }

    private String genEncodeRequestStandard(HttpServletRequest request) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
        {
            sb.append(line);
        }
        return sb.toString();
    }

    private String genEncodeRequest(HttpServletRequest request) throws Exception
    {
        // TODO 可根据实际情况调整请求参数获取方式
        String base64EncodeReq = request.getParameter("data");
        if(StringUtils.isEmpty(base64EncodeReq))
        {
            base64EncodeReq = this.genEncodeRequestStandard(request);
        }

        // TODO RFC Base64 76个字符换行, 临时替换解决
        if(StringUtils.isNotEmpty(base64EncodeReq))
        {
            base64EncodeReq = base64EncodeReq.replaceAll("\r|\n", "");
        }
        return base64EncodeReq;
    }
}
