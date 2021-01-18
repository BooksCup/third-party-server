package com.bc.third.party.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.config.OssConfig;
import com.bc.third.party.server.service.ThirdPartyService;
import com.bc.third.party.server.utils.AvatarUtil;
import com.bc.third.party.server.utils.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 通用
 *
 * @author zhou
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Resource
    private ThirdPartyService thirdPartyService;

    /**
     * 获取UUID
     *
     * @return UUID
     */
    @ApiOperation(value = "获取UUID", notes = "获取UUID")
    @GetMapping(value = "/uuid")
    public ResponseEntity<String> getUUID() {
        String uuid = CommonUtil.generateId();
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

    /**
     * 根据文字生成头像
     *
     * @param content 文字
     * @return 头像
     */
    @ApiOperation(value = "生成头像", notes = "生成头像")
    @PostMapping(value = "/avatar")
    public ResponseEntity<String> generateAvatar(@RequestParam String content) {
        ResponseEntity<String> responseEntity;
        try {
            ThirdPartyConfig thirdPartyConfig = thirdPartyService.getThirdPartyConfig(Constant.CONFIG_KEY_OSS);
            OssConfig ossConfig = JSON.parseObject(thirdPartyConfig.getValue(), OssConfig.class);
            String result = AvatarUtil.generateImageAndUploadOss(content, 100, 100, 0, ossConfig);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[generateAvatar] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
