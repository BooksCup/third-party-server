package com.bc.third.party.server.controller.notify;

import com.bc.third.party.server.entity.NotifyConfig;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.NotifyConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 通知配置
 *
 * @author zhou
 */
@RestController
@RequestMapping("/notifyConfig")
public class NotifyConfigController {

    @Resource
    private NotifyConfigService notifyConfigService;

    /**
     * 新增通知配置
     *
     * @param type   通知类型
     * @param status 是否启用
     * @param data   配置参数(json格式)
     * @return ResponseEntity
     */
    @ApiOperation(value = "新增通知配置", notes = "新增通知配置")
    @PostMapping(value = "")
    public ResponseEntity<String> addNotifyConfig(
            @RequestParam String type,
            @RequestParam String status,
            @RequestParam String data) {
        ResponseEntity<String> responseEntity;
        try {
            NotifyConfig notifyConfig = new NotifyConfig(type, status, data);
            notifyConfigService.addNotifyConfig(notifyConfig);

            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_NOTIFY_CONFIG_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_NOTIFY_CONFIG_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
