package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.ThirdPartyConfig;

/**
 * 系统配置
 *
 * @author zhou
 */
public interface ThirdPartyConfigService {

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
