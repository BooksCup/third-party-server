package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.NotifyConfig;
import com.bc.third.party.server.mapper.NotifyConfigMapper;
import com.bc.third.party.server.service.NotifyConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通知配置
 *
 * @author zhou
 */
@Service("notifyConfigService")
public class NotifyConfigServiceImpl implements NotifyConfigService {

    @Resource
    private NotifyConfigMapper notifyConfigMapper;

    /**
     * 新增通知配置
     *
     * @param notifyConfig 通知配置
     */
    @Override
    public void addNotifyConfig(NotifyConfig notifyConfig) {
        notifyConfigMapper.addNotifyConfig(notifyConfig);
    }

    /**
     * 根据通知类型获取通知配置
     *
     * @param type 通知类型
     * @return 通知配置
     */
    @Override
    public NotifyConfig getNotifyConfigByType(String type) {
        return notifyConfigMapper.getNotifyConfigByType(type);
    }
}
