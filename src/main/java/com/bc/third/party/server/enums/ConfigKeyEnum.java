package com.bc.third.party.server.enums;

/**
 * 配置key
 *
 * @author zhou
 */
public enum ConfigKeyEnum {

    /**
     * 配置key
     */
    TIANYANCHA("TIANYANCHA", "天眼查"),
    KUAIDI100("KUAIDI100", "快递100"),
    FEIE("FEIE", "飞鹅打印机"),
    OSS("OSS", "对象存储"),
    OCR("OCR", "文字识别"),
    ALI_SMS("ALI_SMS", "阿里短信服务"),
    ;

    private String code;
    private String name;

    ConfigKeyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code) {
        ConfigKeyEnum[] configKeyEnums = values();
        for (ConfigKeyEnum configKeyEnum : configKeyEnums) {
            if (configKeyEnum.getCode().equals(code)) {
                return configKeyEnum.getName();
            }
        }
        return null;
    }

}