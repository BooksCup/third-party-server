package com.bc.third.party.server.entity.express.kuaidi100;


import java.util.List;

/**
 * 查询物流轨迹信息返回
 *
 * @author zhou
 */
public class QueryTrackResult {
    private String message;
    private Integer state;
    private Integer ischeck;
    private String com;
    private String nu;
    private List<QueryTrackData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIscheck() {
        return ischeck;
    }

    public void setIscheck(Integer ischeck) {
        this.ischeck = ischeck;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public List<QueryTrackData> getData() {
        return data;
    }

    public void setData(List<QueryTrackData> data) {
        this.data = data;
    }
}
