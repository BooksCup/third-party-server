package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.company.tyc.TycCompany;
import com.bc.third.party.server.entity.company.tyc.TycCompanyChangeInfo;
import com.bc.third.party.server.entity.company.tyc.TycCompanyHolder;
import com.bc.third.party.server.entity.company.tyc.TycCompanyLawSuit;

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
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业基本信息
     */
    TycCompany getTycCompanyByCompanyId(String token, String companyId);

    /**
     * 通过ID获取企业基本信息(DB)
     *
     * @param companyId 企业ID
     * @return 企业基本信息
     */
    TycCompany getTycCompanyByCompanyId(String companyId);

    /**
     * 通过ID获取企业股东列表(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业股东列表
     */
    List<TycCompanyHolder> getTycCompanyHolderListByCompanyId(String token, String companyId);

    /**
     * 通过ID获取企业变更记录列表(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业变更记录列表
     */
    List<TycCompanyChangeInfo> getTycCompanyChangeInfoListByCompanyId(String token, String companyId);

    /**
     * 通过ID获取企业法律诉讼列表(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业法律诉讼列表
     */
    List<TycCompanyLawSuit> getTycCompanyLawSuitListByCompanyId(String token, String companyId);

    /**
     * 新增企业股东
     *
     * @param tycCompanyHolder 企业股东
     */
    void addTycCompanyHolder(TycCompanyHolder tycCompanyHolder);

    /**
     * 新增企业变更记录
     *
     * @param tycCompanyChangeInfo 企业变更记录
     */
    void addTycCompanyChangeInfo(TycCompanyChangeInfo tycCompanyChangeInfo);

    /**
     * 新增企业法律诉讼
     *
     * @param tycCompanyLawSuit 企业法律诉讼
     */
    void addTycCompanyLawSuit(TycCompanyLawSuit tycCompanyLawSuit);

    /**
     * 根据企业ID获取企业股东列表(DB)
     *
     * @param companyId 企业ID
     * @return 企业股东列表
     */
    List<TycCompanyHolder> getTycCompanyHolderListByCompanyId(String companyId);

    /**
     * 根据企业ID获取企业变更记录列表(DB)
     *
     * @param companyId 企业ID
     * @return 企业变更记录列表
     */
    List<TycCompanyChangeInfo> getTycCompanyChangeInfoListByCompanyId(String companyId);

    /**
     * 根据企业ID获取企业法律诉讼列表(DB)
     *
     * @param companyId 企业ID
     * @return 企业法律诉讼列表
     */
    List<TycCompanyLawSuit> getTycCompanyLawSuitListByCompanyId(String companyId);

}
