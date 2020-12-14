package com.bc.third.party.server.entity;

/**
 * 短信配置
 *
 * @author zhou
 */
public class SmsConfig {

    private String regionId;
    private String accessKeyId;
    private String secret;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
