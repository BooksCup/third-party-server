package com.bc.third.party.server.controller.sms;

import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.alisms.SmsLog;
import com.bc.third.party.server.service.SmsLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送日志
 *
 * @author zhou
 */
@RestController
@RequestMapping("/smsLog")
public class SmsLogController {

    @Resource
    SmsLogService smsLogService;

    /**
     * 获取短信发送日志分页信息
     *
     * @param page  当前分页数
     * @param limit 分页大小
     * @return 短信发送日志分页信息
     */
    @ApiOperation(value = "获取短信发送日志分页信息", notes = "获取短信发送日志分页信息")
    @GetMapping(value = "")
    @CrossOrigin
    public ResponseEntity<PageInfo<SmsLog>> getSmsLogPageInfo(
            @RequestParam Integer page,
            @RequestParam Integer limit) {
        ResponseEntity<PageInfo<SmsLog>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            PageInfo<SmsLog> regionPageInfo = smsLogService.getSmsLogPageInfo(page, limit, paramMap);
            responseEntity = new ResponseEntity<>(regionPageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}