package com.mafia.core.log;

import com.mafia.core.constant.ModuleType;
import com.mafia.core.exception.MafiaException;
import com.mafia.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final static String FORMAT = "'{}'.'{}'.'{}',{}";
    private final static String DEFAULT_IDX = "NA_IDX";
    private final static String DEFAULT_RESP_CODE = "NA_RESP_CODE";

    public void info(Class<?> clazz, Object msg)
    {
        this.infoPri(clazz, DEFAULT_IDX, ModuleType.DEFAULT, DEFAULT_RESP_CODE, msg);
    }

    public void info(Class<?> clazz, ModuleType moduleType, String msg) {
        this.infoPri(clazz, DEFAULT_IDX, moduleType, DEFAULT_RESP_CODE, msg);
    }

    public void info(Class<?> clazz, ModuleType moduleType, String idx, String msg) {
        this.infoPri(clazz, idx, moduleType, DEFAULT_RESP_CODE, msg);
    }

    public void error(Class<?> clazz, Exception e)
    {
        this.errorPri(clazz, DEFAULT_IDX, ModuleType.DEFAULT, DEFAULT_RESP_CODE, e.getMessage(), e);
    }

    public void error(Class<?> clazz, MafiaException mafException)
    {
        this.errorPri(clazz, DEFAULT_IDX, ModuleType.DEFAULT, String.valueOf(mafException.getCode()), mafException.getMessage(), null);
    }

    public void error(Class<?> clazz, Integer code, String msg, Throwable cause)
    {
        this.errorPri(clazz, DEFAULT_IDX, ModuleType.DEFAULT, String.valueOf(code), msg, cause);
    }

    public void debug(Class<?> clazz, Object msg)
    {
        this.getLogger(clazz).debug(FORMAT, DEFAULT_IDX, this.getModuleTypeDesc(ModuleType.DEFAULT), DEFAULT_RESP_CODE, JsonUtil.writeValueQuite(msg));
    }

    private void infoPri(Class<?> clazz, String idx, ModuleType moduleType, String code, Object msgObj)
    {
        String msg = StringUtils.EMPTY;
        if(msgObj != null)
        {
            msg = msgObj instanceof String ? (String) msgObj : JsonUtil.writeValueQuite(msgObj);
        }
        this.getLogger(clazz).info(FORMAT, idx, this.getModuleTypeDesc(moduleType), code, msg);
    }

    private void errorPri(Class<?> clazz, String idx, ModuleType moduleType, String code, String msg, Throwable cause)
    {
        Logger logger = this.getLogger(clazz);
        logger.error(FORMAT, idx, this.getModuleTypeDesc(moduleType), code, msg);
        if(cause != null)
        {
            logger.error(cause.getMessage(), cause);
        }
    }

    private Logger getLogger(Class<?> clazz)
    {
        return LoggerFactory.getLogger(clazz);
    }

    private String getModuleTypeDesc(ModuleType moduleType)
    {
        return moduleType.ordinal() + "-" + moduleType.name();
    }
}
