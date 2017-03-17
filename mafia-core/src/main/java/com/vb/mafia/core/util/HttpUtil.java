package com.vb.mafia.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Charsets;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public abstract class HttpUtil {

    private static final boolean LOGGING_ENABLED = true;
    private static final int NO_RETRY = 0;
    private static final int DEFAULT_TIME_OUT = 10000;
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static HttpRequestFactory requestFactory =
        HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new UrlEncodedParser()));

    public static String httpPostRequest(String url, Object data) {
        return httpRequest(url, data, HttpMethods.POST, DEFAULT_TIME_OUT, NO_RETRY);
    }

    public static String httpPostRequest(String url, Object data, int retryTimes)
    {
        return httpRequest(url, data, HttpMethods.POST, DEFAULT_TIME_OUT, retryTimes);
    }

    private static String httpRequest(String url, Object data, String method, int timeoutMilliseconds/*毫秒*/, int retryTimes) {
        Preconditions.checkArgument(retryTimes <= 10 && retryTimes >= 0, "retryTimes should between 0(include) and 10(include)");
        method = StringUtils.upperCase(method);
        Preconditions.checkArgument(HttpMethod.resolve(method) != null, "http request method error");
        try {
            HttpRequest request = getHttpRequest(url, data, method);
            long start = System.currentTimeMillis();
            String uuid = StringUtils.left(UUID.randomUUID().toString(), 13);
            logger.info("UUID:{}, Request URL:{} , method:{}, Request data:{}", uuid, url, method, JsonUtil.writeValueQuite(data));
            request.setNumberOfRetries(retryTimes);
            request.setConnectTimeout(timeoutMilliseconds);
            request.setLoggingEnabled(LOGGING_ENABLED);
            HttpResponse response = request.execute();
            response.setLoggingEnabled(LOGGING_ENABLED);
            InputStream in = new BufferedInputStream(response.getContent());
            String res = StreamUtils.copyToString(in, Charsets.UTF_8);
            logger.info("UUID:{}, Request cost [{}ms], Response data:{}", uuid, (System.currentTimeMillis() - start), res);
            return res;
        } catch (IOException e) {
            logger.warn("Http request error", e);
        }
        return StringUtils.EMPTY;
    }

    private static HttpRequest getHttpRequest(String url, Object data, String method) throws IOException {
        if (StringUtils.endsWithIgnoreCase(method, HttpMethods.GET)) {
            return requestFactory.buildGetRequest(new GenericUrl(url));
        }
        Map<String, Object> paramMap = genParamMap(data);
        UrlEncodedContent urlEncodedContent = new UrlEncodedContent(paramMap);
        return requestFactory.buildRequest(method, new GenericUrl(url), urlEncodedContent);
    }

    private static Map<String, Object> genParamMap(Object obj)
    {
        Map<String, Object> paramMap = CollectionUtil.newHashMap();
        try {
            String jsonStr = JsonUtil.writeValue(obj);
            paramMap = JsonUtil.parse(jsonStr, new TypeReference<Map<String, Object>>() {
            });
        }
        catch(IOException e)
        {
            logger.error("gen param map error", e);
        }
        return paramMap;
    }

}
