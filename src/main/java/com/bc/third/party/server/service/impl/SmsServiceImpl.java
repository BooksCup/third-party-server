package com.bc.third.party.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.NotifyConfig;
import com.bc.third.party.server.entity.SmsConfig;
import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.entity.alisms.SmsSendDetailDTO;
import com.bc.third.party.server.mapper.NotifyConfigMapper;
import com.bc.third.party.server.mapper.SmsMapper;
import com.bc.third.party.server.service.SmsService;
import com.bc.third.party.server.utils.SmsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 短信
 *
 * @author zhou
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    @Resource
    SmsMapper smsMapper;

    @Resource
    NotifyConfigMapper notifyConfigMapper;

    /**
     * 保存短信回执
     *
     * @param smsResponse 短信回执
     */
    @Override
    public void addSmsResponse(SmsResponse smsResponse) {
        smsMapper.addSmsResponse(smsResponse);
    }


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
    @Override
    public List<SmsSendDetailDTO> querySendDetails(String phone, String bizId,
                                                   String sendDate, String page, String limit) {
        NotifyConfig notifyConfig = notifyConfigMapper.getNotifyConfigByType(Constant.NOTIFY_TYPE_SMS);
        if (null == notifyConfig) {
            return new ArrayList<>();
        }
        SmsConfig smsConfig = JSON.parseObject(notifyConfig.getData(), SmsConfig.class);
        List<SmsSendDetailDTO> smsSendDetailDTOList = SmsUtil.querySendDetails(smsConfig,
                phone, bizId, sendDate, page, limit);
        return smsSendDetailDTOList;
    }

    /**
     * 根据处理状态获取短信回执列表
     *
     * @param state 处理状态
     * @return 短信回执列表
     */
    @Override
    public List<SmsResponse> getSmsResponseListByState(String state) {
        return smsMapper.getSmsResponseListByState(state);
    }

    /**
     * 修改短信回执
     *
     * @param smsResponse 短信回执
     */
    @Override
    public void updateSmsResponse(SmsResponse smsResponse) {
        smsMapper.updateSmsResponse(smsResponse);
    }

}
