package com.bc.third.party.server.controller.ocr;

import com.bc.third.party.server.entity.ocr.BusinessCard;
import com.bc.third.party.server.service.OcrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文字识别/名片识别
 *
 * @author zhou
 */
@RestController
@RequestMapping("/ocr/businessCard")
public class OcrBusinessCardController {

    @Resource
    OcrService ocrService;

    /**
     * 获取名片信息
     *
     * @param imageUrl 名片图片url
     * @return 名片信息
     */
    @CrossOrigin
    @ApiOperation(value = "获取名片信息", notes = "获取名片信息")
    @GetMapping(value = "")
    public ResponseEntity<BusinessCard> getBusinessCardInfo(
            @RequestParam String imageUrl) {
        ResponseEntity<BusinessCard> responseEntity;
        try {
            BusinessCard businessCard = ocrService.getBusinessCardInfo(imageUrl);
            responseEntity = new ResponseEntity<>(businessCard, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new BusinessCard(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}