package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.ThirdPartyConfig;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
public interface ThirdPartyConfigMapper {

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

}