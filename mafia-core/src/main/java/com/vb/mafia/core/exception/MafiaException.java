package com.vb.mafia.core.exception;

import com.vb.mafia.core.constant.RespCode;
import com.vb.mafia.core.util.I18nUtil;

public abstract class MafiaException extends Exception {

    private int code;

    public MafiaException(RespCode respCode, Object... respMsgArgs)
    {
        super(I18nUtil.getMsg(respCode.getMsgCode(), respCode.getMsgCode(), respMsgArgs));
        this.code = respCode.getCode();
    }

    public MafiaException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
