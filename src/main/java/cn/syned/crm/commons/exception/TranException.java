package cn.syned.crm.commons.exception;

import cn.syned.crm.commons.message.TranMessage;

public class TranException extends Exception {
    private final TranMessage tranMessage;

    public TranException(TranMessage tranMessage) {
        super(tranMessage.getMessage());
        this.tranMessage = tranMessage;
    }

    public Integer getCode() {
        return tranMessage.getCode();
    }

    public String getMessage() {
        return tranMessage.getMessage();
    }

}
