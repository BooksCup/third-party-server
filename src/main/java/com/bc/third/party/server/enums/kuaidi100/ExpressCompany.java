package com.bc.third.party.server.enums.kuaidi100;

/**
 * 快递公司编码
 *
 * @author zhou
 */
public enum ExpressCompany {

    /**
     * 快递公司及编码
     */
    YUNDA("yunda", "韵达快递"),
    SHUNFENG("shunfeng", "顺丰速运"),
    JD("jd", "京东物流"),
    YUANTONG("yuantong", "圆通速递"),
    ZHONGTONG("zhongtong", "中通快递"),
    HUITONGKUAIDI("huitongkuaidi", "百世快递"),
    SHENTONG("shentong", "申通快递"),
    ;

    ExpressCompany(String companyCode, String companyName) {
        this.companyCode = companyCode;
        this.companyName = companyName;
    }

    private String companyCode;
    private String companyName;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
