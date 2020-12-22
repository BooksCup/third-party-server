package com.bc.third.party.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.company.tyc.Result;
import com.bc.third.party.server.entity.company.tyc.TycBaseResponse;
import com.bc.third.party.server.entity.company.tyc.TycCompany;
import com.bc.third.party.server.mapper.TycCompanyMapper;
import com.bc.third.party.server.service.TycCompanyService;
import com.bc.third.party.server.utils.HttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * 保存企业
     *
     * @param tycCompany 企业
     */
    @Override
    public void addTycCompany(TycCompany tycCompany) {
        tycCompanyMapper.addTycCompany(tycCompany);
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
     * 通过ID获取企业基本信息
     *
     * @param token 天眼查的token
     * @param id    企业ID
     * @return 企业基本信息
     */
    @Override
    public TycCompany getTycCompanyById(String token, String id) {
        String url = "http://open.api.tianyancha.com/services/open/ic/baseinfo/2.0?id=" + id;
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Authorization", token);
        String result = HttpUtil.doGet(url, headerMap);
        TycBaseResponse<TycCompany> tycBaseResponse = JSON.parseObject(result,
                new TypeReference<TycBaseResponse<TycCompany>>() {
                });
        TycCompany tycCompany = tycBaseResponse.getResult();
        return tycCompany;
    }

}
