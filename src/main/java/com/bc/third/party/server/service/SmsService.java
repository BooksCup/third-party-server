package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.SmsResponse;

/**
 * 短信
 *
 * @author zhou
 */
public interface SmsService {

    /**
     * 保存短信回执
     *
     * @param smsResponse 短信回执
     */
    void addSmsResponse(SmsResponse smsResponse);

}
