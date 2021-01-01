package com.bc.third.party.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.company.tyc.*;
import com.bc.third.party.server.mapper.TycCompanyMapper;
import com.bc.third.party.server.service.TycCompanyService;
import com.bc.third.party.server.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业(天眼查)
 *
 * @author zhou
 */
@Service("tycCompanyService")
public class TycCompanyServiceImpl implements TycCompanyService {

    @Resource
    private TycCompanyMapper tycCompanyMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存企业
     *
     * @param tycCompany 企业
     */
    @Override
    public void addTycCompany(TycCompany tycCompany) {
        mongoTemplate.save(tycCompany);
    }

    /**
     * 通过搜索获取企业列表
     *
     * @param token 天眼查的token
     * @param word  搜索关键字
     * @return 企业列表
     */
    @Override
    public List<TycCompany> getTycCompanyListBySearch(String token, String word) {
        String url = "http://open.api.tianyancha.com/services/open/search/2.0?word=" + word;
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Authorization", token);
        String result = HttpUtil.doGet(url, headerMap);
        TycBaseResponse<Result<TycCompany>> tycBaseResponse = JSON.parseObject(result,
                new TypeReference<TycBaseResponse<Result<TycCompany>>>() {
                });
        List<TycCompany> tycCompanyList = tycBaseResponse.getResult().getItems();
        return tycCompanyList;
    }

    /**
     * 通过ID获取企业基本信息(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业基本信息
     */
    @Override
    public TycCompany getTycCompanyByCompanyId(String token, String companyId) {
        String url = "http://open.api.tianyancha.com/services/open/ic/baseinfo/2.0?id=" + companyId;
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Authorization", token);
        String result = HttpUtil.doGet(url, headerMap);
        TycBaseResponse<TycCompany> tycBaseResponse = JSON.parseObject(result,
                new TypeReference<TycBaseResponse<TycCompany>>() {
                });
        TycCompany tycCompany = tycBaseResponse.getResult();
        return tycCompany;
    }

    /**
     * 通过ID获取企业股东列表(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业股东列表
     */
    @Override
    public List<TycCompanyHolder> getTycCompanyHolderListByCompanyId(String token, String companyId) {
        String url = "http://open.api.tianyancha.com/services/open/ic/holder/2.0?id=" + companyId;
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Authorization", token);
        String result = HttpUtil.doGet(url, headerMap);
        System.out.println(result);
        TycBaseResponse<Result<TycCompanyHolder>> tycBaseResponse = JSON.parseObject(result,
                new TypeReference<TycBaseResponse<Result<TycCompanyHolder>>>() {
                });
        List<TycCompanyHolder> tycCompanyHolderList = tycBaseResponse.getResult().getItems();
        return tycCompanyHolderList;
    }

    /**
     * 通过ID获取企业变更记录列表(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业变更记录列表
     */
    @Override
    public List<TycCompanyChangeInfo> getTycCompanyChangeInfoListByCompanyId(String token, String companyId) {
        String url = "http://open.api.tianyancha.com/services/open/ic/changeinfo/2.0?id=" + companyId;
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Authorization", token);
        String result = HttpUtil.doGet(url, headerMap);

        TycBaseResponse<Result<TycCompanyChangeInfo>> tycBaseResponse = JSON.parseObject(result,
                new TypeReference<TycBaseResponse<Result<TycCompanyChangeInfo>>>() {
                });
        List<TycCompanyChangeInfo> tycCompanyChangeInfoList = tycBaseResponse.getResult().getItems();
        return tycCompanyChangeInfoList;
    }

    /**
     * 通过ID获取企业法律诉讼列表(天眼查)
     *
     * @param token     天眼查的token
     * @param companyId 企业ID
     * @return 企业法律诉讼列表
     */
    @Override
    public List<TycCompanyLawSuit> getTycCompanyLawSuitListByCompanyId(String token, String companyId) {
        String url = "http://open.api.tianyancha.com/services/open/jr/lawSuit/2.0?id=" + companyId;
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Authorization", token);
        String result = HttpUtil.doGet(url, headerMap);
        List<TycCompanyLawSuit> tycCompanyLawSuitList;
        try {
            TycBaseResponse<Result<TycCompanyLawSuit>> tycBaseResponse = JSON.parseObject(result,
                    new TypeReference<TycBaseResponse<Result<TycCompanyLawSuit>>>() {
                    });

            tycCompanyLawSuitList = tycBaseResponse.getResult().getItems();
        } catch (Exception e){
            e.printStackTrace();
            tycCompanyLawSuitList = new ArrayList<>();
        }
        return tycCompanyLawSuitList;
    }


    /**
     * 通过ID获取企业基本信息(DB)
     *
     * @param companyId 企业ID
     * @return 企业基本信息
     */
    @Override
    public TycCompany getTycCompanyByCompanyId(String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(companyId));
        TycCompany tycCompany = mongoTemplate.findOne(query, TycCompany.class);
        return tycCompany;
    }

    /**
     * 新增企业股东
     *
     * @param tycCompanyHolder 企业股东
     */
    @Override
    public void addTycCompanyHolder(TycCompanyHolder tycCompanyHolder) {
        mongoTemplate.save(tycCompanyHolder);
    }

    /**
     * 新增企业变更记录
     *
     * @param tycCompanyChangeInfo 企业变更记录
     */
    @Override
    public void addTycCompanyChangeInfo(TycCompanyChangeInfo tycCompanyChangeInfo) {
        mongoTemplate.save(tycCompanyChangeInfo);
    }

    /**
     * 新增企业法律诉讼
     *
     * @param tycCompanyLawSuit 企业法律诉讼
     */
    @Override
    public void addTycCompanyLawSuit(TycCompanyLawSuit tycCompanyLawSuit) {
        mongoTemplate.save(tycCompanyLawSuit);
    }

    /**
     * 根据企业ID获取企业股东列表(DB)
     *
     * @param companyId 企业ID
     * @return 企业股东列表
     */
    @Override
    public List<TycCompanyHolder> getTycCompanyHolderListByCompanyId(String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(companyId));
        List<TycCompanyHolder> tycCompanyHolderList = mongoTemplate.find(query, TycCompanyHolder.class);
        return tycCompanyHolderList;
    }

    /**
     * 根据企业ID获取企业变更记录列表(DB)
     *
     * @param companyId 企业ID
     * @return 企业变更记录列表
     */
    @Override
    public List<TycCompanyChangeInfo> getTycCompanyChangeInfoListByCompanyId(String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(companyId));
        List<TycCompanyChangeInfo> tycCompanyChangeInfoList = mongoTemplate.find(query, TycCompanyChangeInfo.class);
        return tycCompanyChangeInfoList;
    }

    /**
     * 根据企业ID获取企业法律诉讼列表(DB)
     *
     * @param companyId 企业ID
     * @return 企业法律诉讼列表
     */
    @Override
    public List<TycCompanyLawSuit> getTycCompanyLawSuitListByCompanyId(String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(companyId));
        List<TycCompanyLawSuit> tycCompanyLawSuitList = mongoTemplate.find(query, TycCompanyLawSuit.class);
        return tycCompanyLawSuitList;
    }
}
