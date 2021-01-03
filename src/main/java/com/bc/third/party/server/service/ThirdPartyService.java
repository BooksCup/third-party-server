package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;
import com.github.pagehelper.PageInfo;


/**
 * 第三方服务配置
 *
 * @author zhou
 */
public interface ThirdPartyService {

    /**
     * 新增第三方服务配置
     *
     * @param thirdPartyConfig 第三方服务配置
     */
    void addThirdPartyConfig(ThirdPartyConfig thirdPartyConfig);

    /**
     * 获取第三方服务配置
     *
     * @param key key
     * @return 第三方服务配置
     */
    ThirdPartyConfig getThirdPartyConfig(String key);

    /**
     * 获取第三方服务字典分页信息
     *
     * @return 第三方服务字典分页信息
     */
    PageInfo<ThirdPartyDic> getThirdPartyDicPageInfo(int pageNum, int pageSize);

}
