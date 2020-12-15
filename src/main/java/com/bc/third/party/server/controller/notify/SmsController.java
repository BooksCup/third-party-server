package com.bc.third.party.server.controller.notify;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.NotifyConfig;
import com.bc.third.party.server.entity.SmsConfig;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.NotifyConfigService;
import com.bc.third.party.server.utils.SmsUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信
 *
 * @author zhou
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private NotifyConfigService notifyConfigService;

    /**
     * 发送短信
     *
     * @param phones        接收短信的手机号码,支持对多个手机号码发送短信,手机号码之间以英文逗号(,)分隔。
     * @param signName      短信签名名称
     * @param templateCode  短信模板ID
     * @param templateParam 短信模板变量对应的实际值,JSON格式 eg:{"code":"1111"}
     * @return ResponseEntity
     */
    @ApiOperation(value = "发送短信", notes = "发送短信")
    @PostMapping(value = "")
    public ResponseEntity<String> sendSms(
            @RequestParam String phones,
            @RequestParam String signName,
            @RequestParam String templateCode,
            @RequestParam String templateParam) {
        ResponseEntity<String> responseEntity;
        try {
            NotifyConfig notifyConfig = notifyConfigService.getNotifyConfigByType(Constant.NOTIFY_TYPE_SMS);
            if (null == notifyConfig) {
                return new ResponseEntity<>(ResponseMsg.SMS_CONFIG_NOT_CORRECT.getResponseCode(), HttpStatus.BAD_REQUEST);
            }
            SmsConfig smsConfig = JSON.parseObject(notifyConfig.getData(), SmsConfig.class);
            SmsUtil.sendSms(smsConfig, phones, signName, templateCode, templateParam);
            responseEntity = new ResponseEntity<>(ResponseMsg.SEND_SMS_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.SEND_SMS_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
