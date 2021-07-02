package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.alisms.SmsLog;
import com.bc.third.party.server.mapper.SmsLogMapper;
import com.bc.third.party.server.service.SmsLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 短信发送日志
 *
 * @author zhou
 */
@Service("smsLogService")
public class SmsLogServiceImpl implements SmsLogService {

    @Resource
    SmsLogMapper smsLogMapper;

    /**
     * 获取短信发送日志分页信息
     *
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @param paramMap 参数map
     * @return 短信发送日志分页信息
     */
    @Override
    public PageInfo<SmsLog> getSmsLogPageInfo(int pageNum, int pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);
        List<SmsLog> smsLogList = smsLogMapper.getSmsLogList(paramMap);
        return new PageInfo<>(smsLogList);
    }

}