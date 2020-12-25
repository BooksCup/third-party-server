package com.bc.third.party.server.entity.company.tyc;

import java.util.List;

/**
 * 企业信息
 * 包含:企业基本信息,企业股东列表,企业变更记录列表
 *
 * @author zhou
 */
public class TycCompanyProfile {

    private TycCompany tycCompany;
    private List<TycCompanyHolder> tycCompanyHolderList;
    private List<TycCompanyChangeInfo> tycCompanyChangeInfoList;

    public TycCompany getTycCompany() {
        return tycCompany;
    }

    public void setTycCompany(TycCompany tycCompany) {
        this.tycCompany = tycCompany;
    }

    public List<TycCompanyHolder> getTycCompanyHolderList() {
        return tycCompanyHolderList;
    }

    public void setTycCompanyHolderList(List<TycCompanyHolder> tycCompanyHolderList) {
        this.tycCompanyHolderList = tycCompanyHolderList;
    }

    public List<TycCompanyChangeInfo> getTycCompanyChangeInfoList() {
        return tycCompanyChangeInfoList;
    }

    public void setTycCompanyChangeInfoList(List<TycCompanyChangeInfo> tycCompanyChangeInfoList) {
        this.tycCompanyChangeInfoList = tycCompanyChangeInfoList;
    }
}
