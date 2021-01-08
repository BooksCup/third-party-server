package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;
import com.bc.third.party.server.mapper.ThirdPartyMapper;
import com.bc.third.party.server.service.ThirdPartyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
@Service("thirdPartyService")
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Resource
    private ThirdPartyMapper thirdPartyMapper;

    /**
     * 新增第三方服务配置
     *
     * @param thirdPartyConfig 第三方服务配置
     */
    @Override
    public void addThirdPartyConfig(ThirdPartyConfig thirdPartyConfig) {
        thirdPartyMapper.addThirdPartyConfig(thirdPartyConfig);
    }


    /**
     * 获取第三方服务配置
     *
     * @param key key
     * @return 第三方服务配置
     */
    @Override
    public ThirdPartyConfig getThirdPartyConfig(String key) {
        return thirdPartyMapper.getThirdPartyConfig(key);
    }

    /**
     * 获取第三方服务字典列表
     *
     * @return 第三方服务字典列表
     */
    @Override
    public List<ThirdPartyDic> getThirdPartyDicList() {
        return thirdPartyMapper.getThirdPartyDicList();
    }

}
