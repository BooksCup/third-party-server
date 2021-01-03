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
     * 获取第三方服务字典分页信息
     *
     * @return 第三方服务字典分页信息
     */
    @Override
    public PageInfo<ThirdPartyDic> getThirdPartyDicPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        SystemConfig systemConfig = systemConfigMapper.getSystemConfig();
        List<ThirdPartyDic> thirdPartyDicList = thirdPartyMapper.getThirdPartyDicList();
        for (ThirdPartyDic thirdPartyDic : thirdPartyDicList) {
            thirdPartyDic.setLogo(systemConfig.getResourceDomain() + thirdPartyDic.getLogo());
        }
        return new PageInfo<>(thirdPartyDicList);
    }

}
