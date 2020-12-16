package com.bc.third.party.server.mapper;


import com.bc.third.party.server.entity.Cron;

import java.util.List;

/**
 * 定时任务
 *
 * @author zhou
 */
public interface CronMapper {
    /**
     * 获取所有定时任务列表
     *
     * @return 定时任务列表
     */
    List<Cron> getCronList();

    /**
     * 根据任务ID获取定时任务
     *
     * @param cronId 任务ID
     * @return 定时任务
     */
    Cron getCronById(String cronId);
}
