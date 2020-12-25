package com.bc.third.party.server.entity.company.tyc;

/**
 * 企业变更记录
 *
 * @author zhou
 */
public class TycCompanyChangeInfo {

    private String id;
    private String companyId;
    private String changeTime;
    private String contentAfter;
    private String createTime;
    private String contentBefore;
    private String changeItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getContentAfter() {
        return contentAfter;
    }

    public void setContentAfter(String contentAfter) {
        this.contentAfter = contentAfter;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContentBefore() {
        return contentBefore;
    }

    public void setContentBefore(String contentBefore) {
        this.contentBefore = contentBefore;
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

}
