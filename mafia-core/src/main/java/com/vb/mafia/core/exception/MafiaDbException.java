package com.vb.mafia.core.exception;

import com.vb.mafia.core.constant.RespCode;

public class MafiaDbException extends MafiaException {

    public MafiaDbException(RespCode respCode, Object... respMsgArgs) {
        super(respCode, respMsgArgs);
    }
}
