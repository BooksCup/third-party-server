package com.bc.third.party.server.controller.notify;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.NotifyConfig;
import com.bc.third.party.server.entity.SmsConfig;
import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.entity.alisms.SmsSendDetailDTO;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.NotifyConfigService;
import com.bc.third.party.server.service.SmsService;
import com.bc.third.party.server.utils.SmsUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private SmsService smsService;

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
            SmsResponse smsResponse = SmsUtil.sendSms(smsConfig, phones, signName, templateCode, templateParam);
            smsResponse.setPhone(phones);

            // 持久化smsResponse
            smsService.addSmsResponse(smsResponse);

            responseEntity = new ResponseEntity<>(ResponseMsg.SEND_SMS_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.SEND_SMS_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取短信发送记录和发送状态
     * @param phone  接收短信的手机号码
     * @param sendDate 短信发送日期
     * @param bizId
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "获取短信发送记录和发送状态", notes = "获取短信发送记录和发送状态")
    @PostMapping(value = "/sendDetails")
    public ResponseEntity<List<SmsSendDetailDTO>> querySendDetails(
            @RequestParam String phone,
            @RequestParam String sendDate,
            @RequestParam(required = false) String bizId,
            @RequestParam(required = false, defaultValue = "1") String page,
            @RequestParam(required = false, defaultValue = "10") String limit) {
        ResponseEntity<List<SmsSendDetailDTO>> responseEntity;
        try {
            List<SmsSendDetailDTO> smsSendDetailDTOList = smsService.querySendDetails(phone, bizId, sendDate, page, limit);
            responseEntity = new ResponseEntity<>(smsSendDetailDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
