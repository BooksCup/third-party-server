package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;
import com.bc.third.party.server.mapper.SystemConfigMapper;
import com.bc.third.party.server.mapper.ThirdPartyMapper;
import com.bc.third.party.server.service.ThirdPartyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
@Service("thirdPartyService")
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Resource
    private ThirdPartyMapper thirdPartyMapper;

    @Resource
    private SystemConfigMapper systemConfigMapper;

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
     * 根据配置ID获取第三方服务配置
     *
     * @param configId 配置ID
     * @return 第三方服务配置
     */
    @Override
    public ThirdPartyConfig getThirdPartyConfigByConfigId(String configId) {
        return thirdPartyMapper.getThirdPartyConfigByConfigId(configId);
    }

    /**
     * 获取第三方服务字典分页信息
     *
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @param paramMap 参数map
     * @return 第三方服务字典分页信息
     */
    @Override
    public PageInfo<ThirdPartyDic> getThirdPartyDicPageInfo(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);
        SystemConfig systemConfig = systemConfigMapper.getSystemConfig();
        List<ThirdPartyDic> thirdPartyDicList = thirdPartyMapper.getThirdPartyDicList(paramMap);
        for (ThirdPartyDic thirdPartyDic : thirdPartyDicList) {
            thirdPartyDic.setLogo(systemConfig.getResourceDomain() + thirdPartyDic.getLogo());
        }
        return new PageInfo<>(thirdPartyDicList);
    }

    /**
     * 修改第三方服务配置
     *
     * @param thirdPartyConfig 第三方服务配置
     */
    @Override
    public void updateThirdPartyConfig(ThirdPartyConfig thirdPartyConfig) {
        thirdPartyMapper.updateThirdPartyConfig(thirdPartyConfig);
    }

}
