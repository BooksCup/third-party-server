package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;
import com.bc.third.party.server.mapper.ThirdPartyConfigMapper;
import com.bc.third.party.server.service.ThirdPartyConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
@Service("thirdPartyConfigService")
public class ThirdPartyConfigServiceImpl implements ThirdPartyConfigService {

    @Resource
    private ThirdPartyConfigMapper thirdPartyConfigMapper;

    /**
     * 新增第三方服务配置
     *
     * @param thirdPartyConfig 第三方服务配置
     */
    @Override
    public void addThirdPartyConfig(ThirdPartyConfig thirdPartyConfig) {
        thirdPartyConfigMapper.addThirdPartyConfig(thirdPartyConfig);
    }


    /**
     * 获取第三方服务配置
     *
     * @param key key
     * @return 第三方服务配置
     */
    @Override
    public ThirdPartyConfig getThirdPartyConfig(String key) {
        return thirdPartyConfigMapper.getThirdPartyConfig(key);
    }

    /**
     * 获取第三方服务字典列表
     *
     * @return 第三方服务字典列表
     */
    @Override
    public List<ThirdPartyDic> getThirdPartyDicList() {
        return thirdPartyConfigMapper.getThirdPartyDicList();
    }

}
