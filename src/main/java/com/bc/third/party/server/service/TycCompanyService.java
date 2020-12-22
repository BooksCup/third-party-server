package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.company.tyc.TycCompany;

import java.util.List;

/**
 * 企业(天眼查)
 *
 * @author zhou
 */
public interface TycCompanyService {

    /**
     * 保存企业
     *
     * @param tycCompany 企业
     */
    void addTycCompany(TycCompany tycCompany);

    /**
     * 通过搜索获取企业列表
     *
     * @param token 天眼查的token
     * @param word  搜索关键字
     * @return 企业列表
     */
    List<TycCompany> getTycCompanyListBySearch(String token, String word);

    /**
     * 通过ID获取企业基本信息(天眼查)
     *
     * @param token 天眼查的token
     * @param id    企业ID
     * @return 企业基本信息
     */
    TycCompany getTycCompanyById(String token, String id);

    /**
     * 通过ID获取企业基本信息(DB)
     *
     * @param id 企业ID
     * @return 企业基本信息
     */
    TycCompany getTycCompanyById(String id);

}
