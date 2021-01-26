package cn.syned.crm.commons.message;

public enum UserMessage {

    USER_LOGIN_USERNAME_EMPTY("001-001", "用户名为空"),
    USER_LOGIN_PASSWORD_EMPTY("001-002", "密码为空"),
    USER_LOGIN_USERNAME_OR_PASSWORD_INCORRECT("001-003", "用户名或密码错误"),
    USER_LOGIN_ACCOUNT_OVERDUE("001-004", "账户已过期"),
    USER_LOGIN_ACCOUNT_LOCKED("001-005", "账户被锁定"),
    USER_LOGIN_IP_BLOCKED("001-006", "IP地址被锁定"),
    USER_LOGIN_UNKNOWN_EXCEPTION("001-999", "未知错误");

    private final String code;
    private final String message;

    UserMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
