package com.mafia.core.exception;

import com.mafia.core.constant.RespCode;

public class MafiaDbException extends MafiaException {

    public MafiaDbException(RespCode respCode, Object... respMsgArgs) {
        super(respCode, respMsgArgs);
    }
}
