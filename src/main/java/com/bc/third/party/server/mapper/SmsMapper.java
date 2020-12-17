package com.bc.third.party.server.mapper;

import com.bc.third.party.server.entity.SmsResponse;

import java.util.List;

/**
 * 短信
 *
 * @author zhou
 */
public interface SmsMapper {

    /**
     * 保存短信回执
     *
     * @param smsResponse 短信回执
     */
    void addSmsResponse(SmsResponse smsResponse);

    /**
     * 根据未处理短信回执列表
     *
     * @param state 处理状态
     * @return 未处理短信回执列表
     */
    List<SmsResponse> getPendingSmsResponseList(String state);

    /**
     * 修改短信回执
     *
     * @param smsResponse 短信回执
     */
    void updateSmsResponse(SmsResponse smsResponse);

}
