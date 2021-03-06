package com.bc.third.party.server.entity.config;

/**
 * 飞鹅打印机配置信息
 *
 * @author zhou
 */
public class FeieConfig {

    private String user;
    private String key;

    public FeieConfig() {

    }

    public FeieConfig(String user, String key) {
        this.user = user;
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
