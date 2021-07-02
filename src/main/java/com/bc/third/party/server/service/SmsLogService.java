package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.alisms.SmsLog;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 短信发送日志
 *
 * @author zhou
 */
public interface SmsLogService {

    /**
     * 获取短信发送日志分页信息
     *
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @param paramMap 参数map
     * @return 短信发送日志分页信息
     */
    PageInfo<SmsLog> getSmsLogPageInfo(int pageNum, int pageSize, Map<String, Object> paramMap);

}