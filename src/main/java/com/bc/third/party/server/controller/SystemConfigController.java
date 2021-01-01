package com.bc.third.party.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.config.Kuaidi100Config;
import com.bc.third.party.server.entity.config.TycConfig;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.SystemConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统配置
 *
 * @author zhou
 */
@RestController
@RequestMapping("/systemConfig")
public class SystemConfigController {

    @Resource
    private SystemConfigService systemConfigService;

    @ApiOperation(value = "新增系统配置", notes = "新增系统配置")
    @PostMapping(value = "")
    public ResponseEntity<String> addSystemConfig(
            @RequestParam String configType,
            @RequestParam(required = false) String token,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String customer) {
        ResponseEntity<String> responseEntity;
        try {
            String value;
            switch (configType) {
                case Constant.CONFIG_KEY_TIANYANCHA:
                    TycConfig tycConfig = new TycConfig(token);
                    value = JSON.toJSONString(tycConfig);
                    break;
                case Constant.CONFIG_KEY_KUAIDI100:
                    Kuaidi100Config kuaidi100Config = new Kuaidi100Config(key, customer);
                    value = JSON.toJSONString(kuaidi100Config);
                    break;
                default:
                    value = "";
                    break;
            }
            SystemConfig systemConfig = new SystemConfig(configType, value);
            systemConfigService.addSystemConfig(systemConfig);

            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SYSTEM_CONFIG_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SYSTEM_CONFIG_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
