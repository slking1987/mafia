package com.mafia.core.exception;

import com.mafia.core.constant.RespCode;

/**
 * Created by shaolin on 2017/4/12.
 */
public class MafiaInterfaceException extends MafiaException {

    public MafiaInterfaceException(RespCode respCode, Object... respMsgArgs) {
        super(respCode, respMsgArgs);
    }
}
