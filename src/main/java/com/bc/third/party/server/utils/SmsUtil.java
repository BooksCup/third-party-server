package com.bc.third.party.server.utils;

import com.alibaba.fastjson.JSON;
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
}
