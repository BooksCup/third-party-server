package com.bc.third.party.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.bc.third.party.server.entity.SmsConfig;
import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.entity.alisms.SmsSendDetailDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 短信工具类
 *
 * @author zhou
 */
public class SmsUtil {

    public static SmsResponse sendSms(SmsConfig smsConfig, String phones, String signName, String templateCode, String templateParam) {
        DefaultProfile profile = DefaultProfile.getProfile(smsConfig.getRegionId(),
                smsConfig.getAccessKeyId(), smsConfig.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsConfig.getRegionId());
        request.putQueryParameter("PhoneNumbers", phones);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            SmsResponse smsResponse = generateSmsResponse(response);
            return smsResponse;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询短信发送记录和发送状态
     *
     * @param smsConfig   短信配置
     * @param phone       接收短信的手机号码
     * @param bizId       发送回执ID，即发送流水号
     * @param sendDate    短信发送日期
     * @param currentPage 发送记录的的当前页码
     * @param pageSize    每页显示的短信记录数量
     * @return 短信发送记录和发送状态列表
     */
    public static List<SmsSendDetailDTO> querySendDetails(SmsConfig smsConfig, String phone, String bizId,
                                                          String sendDate, String currentPage, String pageSize) {
        DefaultProfile profile = DefaultProfile.getProfile(smsConfig.getRegionId(),
                smsConfig.getAccessKeyId(), smsConfig.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("RegionId", smsConfig.getRegionId());
        request.putQueryParameter("PhoneNumber", phone);
        request.putQueryParameter("SendDate", sendDate);
        request.putQueryParameter("PageSize", pageSize);
        request.putQueryParameter("CurrentPage", currentPage);
        if (!StringUtils.isEmpty(bizId)) {
            request.putQueryParameter("BizId", bizId);
        }
        try {
            CommonResponse response = client.getCommonResponse(request);
            List<SmsSendDetailDTO> smsSendDetailDTOList = generateSmsSendDetailDTOList(response);
            return smsSendDetailDTOList;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static SmsResponse generateSmsResponse(CommonResponse response) {
        if (null == response) {
            return null;
        }
        Map<String, String> responseMap = JSON.parseObject(response.getData(), Map.class);
        if (null == responseMap) {
            return null;
        }
        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setId(CommonUtil.generateId());
        smsResponse.setBizId(responseMap.get("BizId"));
        smsResponse.setCode(responseMap.get("Code"));
        smsResponse.setMessage(responseMap.get("Message"));
        smsResponse.setRequestId(responseMap.get("RequestId"));
        return smsResponse;
    }

    /**
     * 生成短信发送记录和发送状态列表
     *
     * @param response 通用响应
     * @return 短信发送记录和发送状态列表
     */
    private static List<SmsSendDetailDTO> generateSmsSendDetailDTOList(CommonResponse response) {
        List<SmsSendDetailDTO> smsSendDetailDTOList;
        if (null == response) {
            return new ArrayList<>();
        }
        JSONObject responseMap = JSON.parseObject(response.getData());
        if (null == responseMap) {
            return new ArrayList<>();
        }
        try {
            smsSendDetailDTOList = JSON.parseArray(
                    responseMap.getJSONObject("SmsSendDetailDTOs").getJSONArray("SmsSendDetailDTO").toJSONString(), SmsSendDetailDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            smsSendDetailDTOList = new ArrayList<>();
        }
        return smsSendDetailDTOList;
    }

}
