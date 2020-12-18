package com.bc.third.party.server.controller.company.tyc;

import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.company.tyc.TycCompany;
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
     * @param id 企业ID
     * @return 企业信息
     */
    @ApiOperation(value = "通过ID获取企业信息", notes = "通过ID获取企业信息")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TycCompanyProfile> getTycCompanyById(
            @PathVariable String id) {
        ResponseEntity<TycCompanyProfile> responseEntity;
        TycCompanyProfile tycCompanyProfile = new TycCompanyProfile();
        try {
            SystemConfig systemConfig = systemConfigService.getSystemConfig();

            // 企业基本信息
            TycCompany tycCompany = tycCompanyService.getTycCompanyByCompanyId(id);
            if (null == tycCompany) {
                tycCompany = tycCompanyService.getTycCompanyByCompanyId(systemConfig.getTycToken(), id);
                tycCompany.setCompanyId(tycCompany.getId());
                tycCompany.setId(CommonUtil.generateId());
                tycCompanyService.addTycCompany(tycCompany);
            }
            tycCompanyProfile.setTycCompany(tycCompany);

            // 企业股东
            List<TycCompanyHolder> tycCompanyHolderList = tycCompanyService.getTycCompanyHolderListByCompanyId(id);
            if (CollectionUtils.isEmpty(tycCompanyHolderList)) {
                tycCompanyHolderList = tycCompanyService.getTycCompanyHolderByCompanyId(systemConfig.getTycToken(), id);
                for (TycCompanyHolder tycCompanyHolder : tycCompanyHolderList) {
                    tycCompanyHolder.setHolderId(tycCompanyHolder.getId());
                    tycCompanyHolder.setCompanyId(id);
                    tycCompanyHolder.setId(CommonUtil.generateId());
                    tycCompanyService.addTycCompanyHolder(tycCompanyHolder);
                }
            }
            tycCompanyProfile.setTycCompanyHolder(tycCompanyHolderList);

            responseEntity = new ResponseEntity<>(tycCompanyProfile, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new TycCompanyProfile(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
