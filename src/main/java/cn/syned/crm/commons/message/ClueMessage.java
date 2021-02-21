package cn.syned.crm.commons.message;

public enum ClueMessage {

    CLUE_MESSAGE_SUCCESS(200, "success"),
    CLUE_MESSAGE_CREATE_CONVERT_CLUE_SUCCESS(201, "线索转化成功"),
    CLUE_MESSAGE_CREATE_CUSTOMER_FAILED(400, "创建客户失败"),
    CLUE_MESSAGE_CREATE_TRAN_FAILED(400, "创建客户失败"),
    CLUE_MESSAGE_CREATE_CONTACTS_FAILED(401, "创建联系人失败");

    private final Integer code;
    private final String message;

    ClueMessage(Integer code, String message) {
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
