package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.entity.alisms.SmsSendDetailDTO;

import java.util.List;

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

    /**
     * 查询短信发送记录和发送状态
     *
     * @param phone    接收短信的手机号码
     * @param bizId    发送回执ID，即发送流水号
     * @param sendDate 短信发送日期
     * @param page     发送记录的的当前页码
     * @param limit    每页显示的短信记录数量
     * @return 短信发送记录和发送状态列表
     */
    List<SmsSendDetailDTO> querySendDetails(String phone, String bizId, String sendDate, String page, String limit);

    /**
     * 根据处理状态获取短信回执列表
     *
     * @param state 处理状态
     * @return 短信回执列表
     */
    List<SmsResponse> getSmsResponseListByState(String state);

    /**
     * 修改短信回执
     *
     * @param smsResponse 短信回执
     */
    void updateSmsResponse(SmsResponse smsResponse);

}
