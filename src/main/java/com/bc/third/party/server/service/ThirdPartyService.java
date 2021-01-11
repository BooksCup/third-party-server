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
     * 根据配置ID获取第三方服务配置
     *
     * @param configId 配置ID
     * @return 第三方服务配置
     */
    ThirdPartyConfig getThirdPartyConfigByConfigId(String configId);

    /**
     * 获取第三方服务字典分页信息
     *
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 第三方服务字典分页信息
     */
    PageInfo<ThirdPartyDic> getThirdPartyDicPageInfo(int pageNum, int pageSize);

    /**
     * 修改第三方服务配置
     *
     * @param thirdPartyConfig 第三方服务配置
     */
    void updateThirdPartyConfig(ThirdPartyConfig thirdPartyConfig);

}
