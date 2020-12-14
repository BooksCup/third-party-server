package com.bc.third.party.server.controller;

import com.bc.third.party.server.entity.NotifyConfig;
import com.bc.third.party.server.service.NotifyConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
