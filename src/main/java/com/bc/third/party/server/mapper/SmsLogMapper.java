package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.alisms.SmsLog;

import java.util.List;
import java.util.Map;

/**
 * 短信发送日志
 *
 * @author zhou
 */
public interface SmsLogMapper {

    /**
     * 获取短信发送日志列表
     *
     * @param paramMap 参数map
     * @return 短信发送日志列表
     */
    List<SmsLog> getSmsLogList(Map<String, Object> paramMap);

}