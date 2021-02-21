package cn.syned.crm.commons.exception;

import cn.syned.crm.commons.message.ClueMessage;

public class ClueException extends Exception {
    private final ClueMessage clueMessage;

    public ClueException(ClueMessage clueMessage) {
        super(clueMessage.getMessage());
        this.clueMessage = clueMessage;
    }

    public Integer getCode() {
        return clueMessage.getCode();
    }

    public String getMessage() {
        return clueMessage.getMessage();
    }
}
