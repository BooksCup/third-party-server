package com.bc.third.party.server.entity.config;

/**
 * 天眼查配置
 *
 * @author zhou
 */
public class TycConfig {

    private String token;

    public TycConfig() {

    }

    public TycConfig(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
