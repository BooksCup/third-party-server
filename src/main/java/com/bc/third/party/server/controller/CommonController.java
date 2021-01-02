package com.bc.third.party.server.controller;

import com.bc.third.party.server.utils.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 通用
 *
 * @author zhou
 */
@RestController
@RequestMapping("/common")
public class CommonController {

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

}
