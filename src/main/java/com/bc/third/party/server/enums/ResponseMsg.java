package com.bc.third.party.server.enums;

/**
 * 返回消息
 *
 * @author zhou
 */
public enum ResponseMsg {

    /**
     * mall-server接口返回信息
     */
    ADD_SYSTEM_CONFIG_SUCCESS("ADD_SYSTEM_CONFIG_SUCCESS", "新增系统配置成功!"),
    ADD_SYSTEM_CONFIG_ERROR("ADD_SYSTEM_CONFIG_ERROR", "新增系统配置失败!"),

    ADD_NOTIFY_CONFIG_SUCCESS("ADD_NOTIFY_CONFIG_SUCCESS", "新增通知配置成功!"),
    ADD_NOTIFY_CONFIG_ERROR("ADD_NOTIFY_CONFIG_ERROR", "新增通知配置失败!"),

    SMS_CONFIG_NOT_CORRECT("SMS_CONFIG_NOT_CORRECT", "短信配置错误!"),

    SEND_SMS_SUCCESS("SEND_SMS_SUCCESS", "发送短信成功"),
    SEND_SMS_ERROR("SEND_SMS_ERROR", "发送短信失败"),

    PRINT_TEMPLATE_SUCCESS("PRINT_TEMPLATE_SUCCESS", "打印模板成功"),
    PRINT_TEMPLATE_ERROR("PRINT_TEMPLATE_ERROR", "打印模板成功"),
    ;

    ResponseMsg(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    private String responseCode;
    private String responseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
