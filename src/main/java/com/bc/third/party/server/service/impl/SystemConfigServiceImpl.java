package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.mapper.SystemConfigMapper;
import com.bc.third.party.server.service.SystemConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统配置
 *
 * @author zhou
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Resource
    private SystemConfigMapper systemConfigMapper;

    /**
     * 获取系统配置
     *
     * @return 系统配置
     */
    @Override
    public SystemConfig getSystemConfig() {
        return systemConfigMapper.getSystemConfig();
    }

}
