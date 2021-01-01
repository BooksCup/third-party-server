package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.SystemConfig;

/**
 * 系统配置
 *
 * @author zhou
 */
public interface SystemConfigMapper {

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
