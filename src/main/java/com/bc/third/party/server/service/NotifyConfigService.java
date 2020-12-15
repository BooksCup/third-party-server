package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.NotifyConfig;

/**
 * 通知配置
 *
 * @author zhou
 */
public interface NotifyConfigService {

    /**
     * 新增通知配置
     *
     * @param notifyConfig 通知配置
     */
    void addNotifyConfig(NotifyConfig notifyConfig);

    /**
     * 根据通知类型获取通知配置
     *
     * @param type 通知类型
     * @return 通知配置
     */
    NotifyConfig getNotifyConfigByType(String type);

}
