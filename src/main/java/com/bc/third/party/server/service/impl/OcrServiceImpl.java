package com.bc.third.party.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.ThirdPartyConfig;
import com.bc.third.party.server.entity.config.OcrConfig;
import com.bc.third.party.server.entity.ocr.BusinessCard;
import com.bc.third.party.server.mapper.ThirdPartyMapper;
import com.bc.third.party.server.service.OcrService;
import com.bc.third.party.server.utils.HttpUtil;
import org.apache.http.client.methods.HttpHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 文字识别
 *
 * @author zhou
 */
@Service("ocrService")
public class OcrServiceImpl implements OcrService {

    private static final Logger logger = LoggerFactory.getLogger(OcrServiceImpl.class);

    @Resource
    ThirdPartyMapper thirdPartyMapper;

    /**
     * 获取名片信息
     *
     * @param imageUrl 名片图片url
     * @return 名片信息
     */
    @Override
    public BusinessCard getBusinessCardInfo(String imageUrl) {
        BusinessCard businessCard;
        ThirdPartyConfig thirdPartyConfig = thirdPartyMapper.getThirdPartyConfig(Constant.CONFIG_KEY_OCR);
        OcrConfig ocrConfig = JSON.parseObject(thirdPartyConfig.getValue(), OcrConfig.class);
        String url = "http://dm-57.data.aliyun.com/rest/160601/ocr/ocr_business_card.json";
        try {
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("image", imageUrl);

            HttpHead httpHead = new HttpHead();
            httpHead.setHeader("Authorization", "APPCODE " + ocrConfig.getAppCode());
            httpHead.setHeader("Content-Type", "application/json");

            String result = HttpUtil.doPost(url, httpHead.getAllHeaders(), JSON.toJSONString(paramMap));
            logger.info("[getBusinessCardInfo], result: " + result);

            businessCard = JSON.parseObject(result, BusinessCard.class);
        } catch (Exception e) {
            e.printStackTrace();
            businessCard = null;
        }
        return businessCard;
    }

}