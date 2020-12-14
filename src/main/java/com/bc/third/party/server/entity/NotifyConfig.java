package com.bc.third.party.server.entity;

import com.bc.third.party.server.utils.CommonUtil;

/**
 * 通知配置
 *
 * @author zhou
 */
public class NotifyConfig {

    private String id;
    private String type;
    private String status;
    private String data;

    public NotifyConfig() {

    }

    public NotifyConfig(String type, String status, String data) {
        this.id = CommonUtil.generateId();
        this.type = type;
        this.status = status;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
