package com.bc.third.party.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;
import com.bc.third.party.server.entity.config.FeieConfig;
import com.bc.third.party.server.entity.config.Kuaidi100Config;
import com.bc.third.party.server.entity.config.TycConfig;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.ThirdPartyConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 第三方服务配置
 *
 * @author zhou
 */
@RestController
@RequestMapping("/thirdParty")
public class ThirdPartyConfigController {

    @Resource
    private ThirdPartyConfigService thirdPartyConfigService;

    @ApiOperation(value = "新增第三方服务配置", notes = "新增第三方服务配置")
    @PostMapping(value = "/config")
    public ResponseEntity<String> addThirdPartyConfig(
            @RequestParam String configType,
            @RequestParam(required = false) String token,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String user) {
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
                case Constant.CONFIG_KEY_FEIE:
                    FeieConfig feieConfig = new FeieConfig(user, key);
                    value = JSON.toJSONString(feieConfig);
                    break;
                default:
                    value = "";
                    break;
            }
            ThirdPartyConfig thirdPartyConfig = new ThirdPartyConfig(configType, value);
            thirdPartyConfigService.addThirdPartyConfig(thirdPartyConfig);

            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_THIRD_PARTY_CONFIG_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_THIRD_PARTY_CONFIG_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取第三方服务字典列表
     *
     * @return 第三方服务字典列表
     */
    @ApiOperation(value = "获取第三方服务字典列表", notes = "获取第三方服务字典列表")
    @GetMapping(value = "/dic")
    public ResponseEntity<List<ThirdPartyDic>> getThirdPartyDicList() {
        ResponseEntity<List<ThirdPartyDic>> responseEntity;
        try {
            List<ThirdPartyDic> thirdPartyDicList = thirdPartyConfigService.getThirdPartyDicList();
            responseEntity = new ResponseEntity<>(thirdPartyDicList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
