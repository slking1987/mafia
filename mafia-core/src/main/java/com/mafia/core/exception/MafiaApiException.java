package com.mafia.core.exception;

import com.mafia.core.constant.RespCode;

public class MafiaApiException extends MafiaException {

    public MafiaApiException(RespCode respCode, Object... respMsgArgs) {
        super(respCode, respMsgArgs);
    }
}
