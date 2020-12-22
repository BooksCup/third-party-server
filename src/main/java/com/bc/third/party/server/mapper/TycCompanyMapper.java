package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.company.tyc.TycCompany;

import java.util.List;

/**
 * 企业(天眼查)
 *
 * @author zhou
 */
public interface TycCompanyMapper {

    /**
     * 保存企业
     *
     * @param tycCompany 企业
     */
    void addTycCompany(TycCompany tycCompany);

    /**
     * 通过ID获取企业基本信息列表
     *
     * @param id 企业ID
     * @return 企业基本信息列表
     */
    List<TycCompany> getTycCompanyListById(String id);

}
