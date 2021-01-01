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
     * 新增系统配置
     *
     * @param systemConfig 系统配置
     */
    @Override
    public void addSystemConfig(SystemConfig systemConfig) {
        systemConfigMapper.addSystemConfig(systemConfig);
    }


    /**
     * 获取系统配置
     *
     * @param key key
     * @return 系统配置
     */
    @Override
    public SystemConfig getSystemConfig(String key) {
        return systemConfigMapper.getSystemConfig(key);
    }

}
