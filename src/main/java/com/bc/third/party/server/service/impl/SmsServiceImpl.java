package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.mapper.SmsMapper;
import com.bc.third.party.server.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信
 *
 * @author zhou
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    @Resource
    SmsMapper smsMapper;

    /**
     * 保存短信回执
     *
     * @param smsResponse 短信回执
     */
    @Override
    public void addSmsResponse(SmsResponse smsResponse) {
        smsMapper.addSmsResponse(smsResponse);
    }

}
