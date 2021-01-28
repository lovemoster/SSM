package cn.syned.crm.commons.exception;

import cn.syned.crm.commons.message.UserMessage;

public class UserException extends Exception {
    private final UserMessage message;

    public UserException(UserMessage message) {
        super(message.getMessage());
        this.message = message;
    }

    public String getMessages() {
        return message.getMessage();
    }

    public String getCode() {
        return message.getCode();
    }
}
