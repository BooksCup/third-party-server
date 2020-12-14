package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.NotifyConfig;

/**
 * 通知配置
 *
 * @author zhou
 */
public interface NotifyConfigMapper {

    /**
     * 新增通知配置
     *
     * @param notifyConfig 通知配置
     */
    void addNotifyConfig(NotifyConfig notifyConfig);

}
