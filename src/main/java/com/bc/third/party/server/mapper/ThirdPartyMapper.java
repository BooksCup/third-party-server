package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;

import java.util.List;
import java.util.Map;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
public interface ThirdPartyMapper {

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
     * 获取第三方服务字典列表
     *
     * @param paramMap 参数map
     * @return 第三方服务字典列表
     */
    List<ThirdPartyDic> getThirdPartyDicList(Map<String, Object> paramMap);

    /**
     * 修改第三方服务配置
     *
     * @param thirdPartyConfig 第三方服务配置
     */
    void updateThirdPartyConfig(ThirdPartyConfig thirdPartyConfig);

}
