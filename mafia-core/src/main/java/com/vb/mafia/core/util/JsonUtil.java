package com.vb.mafia.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class JsonUtil {
    private static final ObjectMapper om = new ObjectMapper();
    private static final ObjectMapper prettyMapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static {
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(String data, TypeReference<T> typeRef) throws IOException {
        return (T) om.readValue(data, typeRef);
    }

    public static String writeValue(Object value) throws IOException {
        return om.writeValueAsString(value);
    }

    public static String writeValueQuite(Object value) {
        String result = StringUtils.EMPTY;
        try {
            result = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("write value quite error", e);
        }
        return result;
    }

    public static <T> T parse(String data, Class<T> clazz) throws IOException {
        return om.readValue(data, clazz);
    }

    public static ObjectMapper getObjectMapper() {
        return om;
    }

    public static String pretty(Object obj) {
        try {
            return prettyMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("prettyMapper parse error", e);
        }
        return StringUtils.EMPTY;
    }

}
