package com.bc.third.party.server.entity;

import com.bc.third.party.server.utils.CommonUtil;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
public class ThirdPartyConfig {

    private String id;
    private String key;
    private String value;

    public ThirdPartyConfig() {

    }

    public ThirdPartyConfig(String key, String value) {
        this.id = CommonUtil.generateId();
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
