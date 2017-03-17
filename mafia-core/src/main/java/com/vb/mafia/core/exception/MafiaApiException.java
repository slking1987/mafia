package com.vb.mafia.core.exception;

import com.vb.mafia.core.constant.RespCode;

public class MafiaApiException extends MafiaException {

    public MafiaApiException(RespCode respCode, Object... respMsgArgs) {
        super(respCode, respMsgArgs);
    }

    public MafiaApiException(int code, String msg) {
        super(code, msg);
    }
}
