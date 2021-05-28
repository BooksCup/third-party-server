package com.bc.third.party.server.controller.oss;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.config.OssConfig;
import com.bc.third.party.server.enums.ConfigKeyEnum;
import com.bc.third.party.server.service.ThirdPartyService;
import com.bc.third.party.server.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 上传文件到oss控制器
 *
 * @author zhou
 */
@RestController
@RequestMapping("file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileUtil fileUtil;

    @Resource
    private ThirdPartyService thirdPartyService;

    /**
     * 多文件上传
     *
     * @param request 请求
     * @return ResponseEntity
     */
    @CrossOrigin
    @PostMapping(value = "")
    public ResponseEntity<List<String>> uploadMultipartFile(HttpServletRequest request) {
        ResponseEntity<List<String>> responseEntity;
        try {
            ThirdPartyConfig thirdPartyConfig = thirdPartyService.getThirdPartyConfig(ConfigKeyEnum.OSS.getCode());
            OssConfig ossConfig = JSON.parseObject(thirdPartyConfig.getValue(), OssConfig.class);

            List<String> fileNameList = fileUtil.uploadMultipartFile(request, ossConfig);
            responseEntity = new ResponseEntity<>(fileNameList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("[upload], 上传文件至OSS错误: ", e);
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}