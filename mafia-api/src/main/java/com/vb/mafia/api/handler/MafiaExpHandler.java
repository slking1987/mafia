package com.vb.mafia.api.handler;

import com.vb.mafia.api.controller.ApiController;
import com.vb.mafia.api.response.ApiResp;
import com.vb.mafia.api.util.ApiUtil;
import com.vb.mafia.core.constant.RespCode;
import com.vb.mafia.core.exception.MafiaException;
import com.vb.mafia.core.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.vb.mafia.core.constant.RespCode.*;

@ControllerAdvice(assignableTypes = {ApiController.class})
@RestController
public class MafiaExpHandler {

    private static final Class LOG_CLASS = MafiaExpHandler.class;

    @Autowired
    private LogService logService;

    @ExceptionHandler(value = {MafiaException.class})
    public ApiResp handleMafException(MafiaException e)
    {
        logService.error(LOG_CLASS, e);
        return ApiUtil.genApiResp(e);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ApiResp handleIllegalArgumentException(IllegalArgumentException e)
    {
        return handleSystemException(EXP_ILLEGAL_ARGUMENT, e);
    }

    @ExceptionHandler(value = {IOException.class})
    public ApiResp handleIOException(IOException e)
    {
        return handleSystemException(EXP_IO, e);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ApiResp handleRuntimeException(RuntimeException e)
    {
        return handleSystemException(EXP_RUNTIME, e);
    }

    @ExceptionHandler(value = {Exception.class})
    public ApiResp handleUnexpectedException(Exception e)
    {
        return handleSystemException(EXP_OTHERS, e);
    }

    private ApiResp handleSystemException(RespCode respCode, Exception e)
    {
        logService.error(LOG_CLASS, e);
        return ApiUtil.genApiResp(respCode, e);
    }
}
