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
     * 通过搜索获取企业列表
     *
     * @param token 天眼查的token
     * @param word  搜索关键字
     * @return 企业列表
     */
    List<TycCompany> getTycCompanyListBySearch(String token, String word);

}
