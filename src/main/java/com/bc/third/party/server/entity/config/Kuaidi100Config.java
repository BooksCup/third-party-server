package com.bc.third.party.server.entity.config;

/**
 * 快递100配置
 *
 * @author zhou
 */
public class Kuaidi100Config {

    private String key;
    private String customer;

    public Kuaidi100Config() {

    }

    public Kuaidi100Config(String key, String customer) {
        this.key = key;
        this.customer = customer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

}
