package com.bc.third.party.server.entity.company.tyc;

import java.util.List;

public class TycCompanyProfile {
    private TycCompany tycCompany;
    private List<TycCompanyHolder> tycCompanyHolder;

    public TycCompany getTycCompany() {
        return tycCompany;
    }

    public void setTycCompany(TycCompany tycCompany) {
        this.tycCompany = tycCompany;
    }

    public List<TycCompanyHolder> getTycCompanyHolder() {
        return tycCompanyHolder;
    }

    public void setTycCompanyHolder(List<TycCompanyHolder> tycCompanyHolder) {
        this.tycCompanyHolder = tycCompanyHolder;
    }
}
