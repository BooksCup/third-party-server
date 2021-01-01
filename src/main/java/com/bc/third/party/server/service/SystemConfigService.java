package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.SystemConfig;

/**
 * 系统配置
 *
 * @author zhou
 */
public interface SystemConfigService {

    /**
     * 新增系统配置
     *
     * @param systemConfig 系统配置
     */
    void addSystemConfig(SystemConfig systemConfig);

    /**
     * 获取系统配置
     *
     * @param key key
     * @return 系统配置
     */
    SystemConfig getSystemConfig(String key);

}
