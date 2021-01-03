package com.bc.third.party.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.ThirdPartyDic;
import com.bc.third.party.server.entity.config.FeieConfig;
import com.bc.third.party.server.entity.config.Kuaidi100Config;
import com.bc.third.party.server.entity.config.TycConfig;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.SystemConfigService;
import com.bc.third.party.server.service.ThirdPartyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方服务
 *
 * @author zhou
 */
@RestController
@RequestMapping("/thirdParty")
public class ThirdPartyController {

    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyController.class);

    @Resource
    private ThirdPartyService thirdPartyService;

    @Resource
    private SystemConfigService systemConfigService;

    /**
     * 新增第三方服务配置
     *
     * @param configType 配置类型
     * @param token      token
     * @param key        key
     * @param customer   customer
     * @param user       user
     * @return ResponseEntity
     */
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
            thirdPartyService.addThirdPartyConfig(thirdPartyConfig);

            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_THIRD_PARTY_CONFIG_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_THIRD_PARTY_CONFIG_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取第三方服务字典分页信息
     *
     * @return 第三方服务字典分页信息
     */
    @CrossOrigin
    @ApiOperation(value = "获取第三方服务字典分页信息", notes = "获取第三方服务字典分页信息")
    @GetMapping(value = "/dic")
    public ResponseEntity<PageInfo<ThirdPartyDic>> getThirdPartyDicPageInfo(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        ResponseEntity<PageInfo<ThirdPartyDic>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("keyword", keyword);
            PageInfo<ThirdPartyDic> thirdPartyDicPageInfo = thirdPartyService.getThirdPartyDicPageInfo(page, limit, paramMap);
            responseEntity = new ResponseEntity<>(thirdPartyDicPageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 修改第三方服务配置
     *
     * @return 修改第三方服务配置
     */
    @CrossOrigin
    @ApiOperation(value = "修改第三方服务配置", notes = "修改第三方服务配置")
    @PutMapping(value = "/config/{configId}")
    public ResponseEntity<String> updateThirdPartyConfig(
            @PathVariable String configId,
            @RequestParam(required = false) String token,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String user) {
        ResponseEntity<String> responseEntity;
        logger.info("[updateThirdPartyConfig], configId: " + configId);
        try {
            ThirdPartyConfig thirdPartyConfig = thirdPartyService.getThirdPartyConfigByConfigId(configId);
            String value;
            switch (thirdPartyConfig.getKey()) {
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
            thirdPartyConfig.setValue(value);
            thirdPartyService.updateThirdPartyConfig(thirdPartyConfig);
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_THIRD_PARTY_CONFIG_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_THIRD_PARTY_CONFIG_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
