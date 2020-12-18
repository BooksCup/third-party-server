package com.bc.third.party.server.entity.company.tyc;

import com.bc.third.party.server.entity.company.tyc.holder.Capital;

import java.util.List;

public class TycCompanyHolder {

    private String holderId;
    private String companyId;
    private String name;
    private String alias;
    private String id;
    private String logo;
    private Integer type;
    private List<Capital> capital;
    private List<Capital> capitalActl;

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Capital> getCapital() {
        return capital;
    }

    public void setCapital(List<Capital> capital) {
        this.capital = capital;
    }

    public List<Capital> getCapitalActl() {
        return capitalActl;
    }

    public void setCapitalActl(List<Capital> capitalActl) {
        this.capitalActl = capitalActl;
    }
}
