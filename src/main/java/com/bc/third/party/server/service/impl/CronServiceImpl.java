package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.Cron;
import com.bc.third.party.server.mapper.CronMapper;
import com.bc.third.party.server.service.CronService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务
 *
 * @author zhou
 */
@Service("cronService")
public class CronServiceImpl implements CronService {

    @Resource
    private CronMapper cronMapper;

    /**
     * 获取所有定时任务列表
     *
     * @return 定时任务列表
     */
    @Override
    public List<Cron> getCronList() {
        return cronMapper.getCronList();
    }

    /**
     * 根据任务ID获取定时任务
     *
     * @param cronId 任务ID
     * @return 定时任务
     */
    @Override
    public Cron getCronById(String cronId) {
        return cronMapper.getCronById(cronId);
    }
}
