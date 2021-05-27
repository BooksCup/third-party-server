package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.ocr.BusinessCard;

/**
 * 文字识别
 *
 * @author zhou
 */
public interface OcrService {

    /**
     * 获取名片信息
     *
     * @param imageUrl 名片图片url
     * @return 名片信息
     */
    BusinessCard getBusinessCardInfo(String imageUrl);

}