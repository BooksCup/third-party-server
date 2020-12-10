package com.bc.third.party.server.controller.company.tyc;

import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.company.tyc.TycCompany;
import com.bc.third.party.server.entity.company.tyc.TycCompanyChangeInfo;
import com.bc.third.party.server.entity.company.tyc.TycCompanyHolder;
import com.bc.third.party.server.entity.company.tyc.TycCompanyProfile;
import com.bc.third.party.server.service.SystemConfigService;
import com.bc.third.party.server.service.TycCompanyService;
import com.bc.third.party.server.utils.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 企业(天眼查)
 *
 * @author zhou
 */
@RestController
@RequestMapping("/tycCompany")
public class TycCompanyController {

    @Resource
    private TycCompanyService tycCompanyService;

    @Resource
    private SystemConfigService systemConfigService;

    /**
     * 通过搜索获取企业列表
     *
     * @param word 搜索关键字
     * @return 企业列表
     */
    @ApiOperation(value = "搜索企业", notes = "搜索企业")
    @GetMapping(value = "")
    public ResponseEntity<List<TycCompany>> searchTycCompany(
            @RequestParam String word) {
        ResponseEntity<List<TycCompany>> responseEntity;
        try {
            SystemConfig systemConfig = systemConfigService.getSystemConfig();

            List<TycCompany> companyList = tycCompanyService.getTycCompanyListBySearch(systemConfig.getTycToken(), word);
            // 持久化?

            responseEntity = new ResponseEntity<>(companyList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 通过ID获取企业信息
     *
     * @param companyId 企业ID
     * @return 企业信息
     */
    @ApiOperation(value = "通过ID获取企业信息", notes = "通过ID获取企业信息")
    @GetMapping(value = "/{companyId}")
    public ResponseEntity<TycCompanyProfile> getTycCompanyById(
            @PathVariable String companyId) {
        ResponseEntity<TycCompanyProfile> responseEntity;
        TycCompanyProfile tycCompanyProfile = new TycCompanyProfile();
        try {
            SystemConfig systemConfig = systemConfigService.getSystemConfig();

            // 企业基本信息
            TycCompany tycCompany = tycCompanyService.getTycCompanyByCompanyId(companyId);
            if (null == tycCompany) {
                tycCompany = tycCompanyService.getTycCompanyByCompanyId(systemConfig.getTycToken(), companyId);
                tycCompany.setCompanyId(tycCompany.getId());
                tycCompany.setId(CommonUtil.generateId());
                tycCompanyService.addTycCompany(tycCompany);
            }
            tycCompanyProfile.setTycCompany(tycCompany);

            // 企业股东列表
            List<TycCompanyHolder> tycCompanyHolderList = getAndSaveTycCompanyHolderList(companyId);
            tycCompanyProfile.setTycCompanyHolderList(tycCompanyHolderList);

            // 变更记录列表
            List<TycCompanyChangeInfo> tycCompanyChangeInfoList = getAndSaveTycCompanyChangeInfoList(companyId);
            tycCompanyProfile.setTycCompanyChangeInfoList(tycCompanyChangeInfoList);

            responseEntity = new ResponseEntity<>(tycCompanyProfile, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new TycCompanyProfile(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取企业股东列表
     *
     * @param companyId 企业ID
     * @return 企业股东列表
     */
    @ApiOperation(value = "获取企业股东列表", notes = "获取企业股东列表")
    @GetMapping(value = "/{companyId}/holder")
    public ResponseEntity<List<TycCompanyHolder>> getTycCompanyHolderListByCompanyId(
            @PathVariable String companyId) {
        ResponseEntity<List<TycCompanyHolder>> responseEntity;
        try {
            List<TycCompanyHolder> tycCompanyHolderList = getAndSaveTycCompanyHolderList(companyId);
            responseEntity = new ResponseEntity<>(tycCompanyHolderList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取企业变更记录列表
     *
     * @param companyId 企业ID
     * @return 企业变更记录列表
     */
    @ApiOperation(value = "获取企业变更记录列表", notes = "获取企业变更记录列表")
    @GetMapping(value = "/{companyId}/changeInfo")
    public ResponseEntity<List<TycCompanyChangeInfo>> getTycCompanyChangeInfoListByCompanyId(
            @PathVariable String companyId) {
        ResponseEntity<List<TycCompanyChangeInfo>> responseEntity;
        try {
            List<TycCompanyChangeInfo> tycCompanyChangeInfoList = getAndSaveTycCompanyChangeInfoList(companyId);
            responseEntity = new ResponseEntity<>(tycCompanyChangeInfoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取并保存企业股东列表
     *
     * @param companyId 企业ID
     * @return 企业股东列表
     */
    private List<TycCompanyHolder> getAndSaveTycCompanyHolderList(String companyId) {
        SystemConfig systemConfig = systemConfigService.getSystemConfig();

        List<TycCompanyHolder> tycCompanyHolderList = tycCompanyService.getTycCompanyHolderListByCompanyId(companyId);
        if (CollectionUtils.isEmpty(tycCompanyHolderList)) {
            tycCompanyHolderList = tycCompanyService.getTycCompanyHolderListByCompanyId(systemConfig.getTycToken(), companyId);
            for (TycCompanyHolder tycCompanyHolder : tycCompanyHolderList) {
                tycCompanyHolder.setHolderId(tycCompanyHolder.getId());
                tycCompanyHolder.setCompanyId(companyId);
                tycCompanyHolder.setId(CommonUtil.generateId());
                tycCompanyService.addTycCompanyHolder(tycCompanyHolder);
            }
        }
        return tycCompanyHolderList;
    }

    /**
     * 获取并保存企业变更记录列表
     *
     * @param companyId 企业ID
     * @return 企业变更记录列表
     */
    private List<TycCompanyChangeInfo> getAndSaveTycCompanyChangeInfoList(String companyId) {
        SystemConfig systemConfig = systemConfigService.getSystemConfig();

        List<TycCompanyChangeInfo> tycCompanyChangeInfoList = tycCompanyService.getTycCompanyChangeInfoListByCompanyId(companyId);
        if (CollectionUtils.isEmpty(tycCompanyChangeInfoList)) {
            tycCompanyChangeInfoList = tycCompanyService.getTycCompanyChangeInfoListByCompanyId(systemConfig.getTycToken(), companyId);
            for (TycCompanyChangeInfo tycCompanyChangeInfo : tycCompanyChangeInfoList) {
                tycCompanyChangeInfo.setCompanyId(companyId);
                tycCompanyChangeInfo.setId(CommonUtil.generateId());
                tycCompanyService.addTycCompanyHolder(tycCompanyChangeInfo);
            }
        }
        return tycCompanyChangeInfoList;
    }

}
