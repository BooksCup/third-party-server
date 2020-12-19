package com.bc.third.party.server.entity.express.kuaidi100;

/**
 * 查询物流轨迹信息返回
 *
 * @author zhou
 */
public class QueryTrackData {
    private String context;
    private String ftime;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
