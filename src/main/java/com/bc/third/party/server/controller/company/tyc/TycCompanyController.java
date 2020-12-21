package com.bc.third.party.server.controller.company.tyc;

import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.company.tyc.TycCompany;
import com.bc.third.party.server.service.SystemConfigService;
import com.bc.third.party.server.service.TycCompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
