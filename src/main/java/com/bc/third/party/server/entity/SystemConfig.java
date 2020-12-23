package com.bc.third.party.server.entity;

/**
 * 系统配置
 *
 * @author zhou
 */
public class SystemConfig {

    private String id;
    private String tycToken;
    private String kuaidi100Key;
    private String kuaidi100Customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTycToken() {
        return tycToken;
    }

    public void setTycToken(String tycToken) {
        this.tycToken = tycToken;
    }

    public String getKuaidi100Key() {
        return kuaidi100Key;
    }

    public void setKuaidi100Key(String kuaidi100Key) {
        this.kuaidi100Key = kuaidi100Key;
    }

    public String getKuaidi100Customer() {
        return kuaidi100Customer;
    }

    public void setKuaidi100Customer(String kuaidi100Customer) {
        this.kuaidi100Customer = kuaidi100Customer;
    }
}
