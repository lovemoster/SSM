package cn.syned.crm.commons.message;

public enum TranMessage {

    TRAN_MESSAGE_SUCCESS(200, "success"),
    TRAN_MESSAGE_FAILED(400, "failed"),
    TRAN_MESSAGE_PARAMETER_INCOMPLETE(401, "参数不完整");

    private final Integer code;
    private final String message;

    TranMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
