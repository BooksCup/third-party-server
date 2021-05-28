package com.bc.third.party.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.SmsConfig;
import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.alisms.SmsSendDetailDTO;
import com.bc.third.party.server.enums.ConfigKeyEnum;
import com.bc.third.party.server.mapper.SmsMapper;
import com.bc.third.party.server.mapper.ThirdPartyMapper;
import com.bc.third.party.server.service.SmsService;
import com.bc.third.party.server.utils.SmsUtil;
import com.bc.third.party.server.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    ThirdPartyMapper thirdPartyMapper;

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
        ThirdPartyConfig thirdPartyConfig = thirdPartyMapper.getThirdPartyConfig(ConfigKeyEnum.ALI_SMS.getCode());
        if (null == thirdPartyConfig) {
            return new ArrayList<>();
        }
        SmsConfig smsConfig = JSON.parseObject(thirdPartyConfig.getValue(), SmsConfig.class);
        List<SmsSendDetailDTO> smsSendDetailDTOList = SmsUtil.querySendDetails(smsConfig,
                phone, bizId, sendDate, page, limit);
        return smsSendDetailDTOList;
    }

    /**
     * 根据未处理短信回执列表
     *
     * @param state 处理状态
     * @return 未处理短信回执列表
     */
    @Override
    public List<SmsResponse> getPendingSmsResponseList(String state) {
        return smsMapper.getPendingSmsResponseList(state);
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

    /**
     * 处理短信回执
     * 查询短信发送记录和发送状态
     */
    @Override
    public void handlePendingSmsResponse() {
        List<SmsResponse> smsResponseList = smsMapper.getPendingSmsResponseList(Constant.SMS_RESPONSE_STATE_PENDING);
        for (SmsResponse smsResponse : smsResponseList) {
            String sendDate = TimeUtil.formatSendTime(smsResponse.getCreateTime());
            List<SmsSendDetailDTO> smsSendDetailDTOList = querySendDetails(smsResponse.getPhone(),
                    smsResponse.getBizId(), sendDate, Constant.DEFAULT_PAGE, Constant.DEFAULT_PAGE_SIZE);

            if (!CollectionUtils.isEmpty(smsSendDetailDTOList)) {
                SmsSendDetailDTO smsSendDetailDTO = smsSendDetailDTOList.get(0);
                smsResponse.setSendDate(smsSendDetailDTO.getSendDate());
                smsResponse.setReceiveDate(smsSendDetailDTO.getReceiveDate());
                smsResponse.setTemplateCode(smsSendDetailDTO.getTemplateCode());
                smsResponse.setContent(smsSendDetailDTO.getContent());
                smsResponse.setStatus(smsSendDetailDTO.getSendStatus());
                smsResponse.setErrCode(smsSendDetailDTO.getErrCode());
            }
            // 更新短信回执处理状态
            smsResponse.setState(Constant.SMS_RESPONSE_STATE_SOLVED);
            smsMapper.updateSmsResponse(smsResponse);
        }
    }

}