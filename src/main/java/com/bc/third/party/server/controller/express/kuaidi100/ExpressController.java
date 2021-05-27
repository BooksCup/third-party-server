package com.bc.third.party.server.controller.express.kuaidi100;

import com.bc.third.party.server.entity.express.kuaidi100.QueryTrackResult;
import com.bc.third.party.server.service.ExpressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 快递(快递100)
 *
 * @author zhou
 */
@RestController
@RequestMapping("/express/kuaidi100")
public class ExpressController {

    @Resource
    private ExpressService expressService;

    /**
     * 查询物流轨迹信息(支持跨域)
     *
     * @param com 快递公司编码
     * @param num 快递单号
     * @return ResponseEntity
     */
    @CrossOrigin
    @ApiOperation(value = "查询物流轨迹信息(支持跨域)", notes = "查询物流轨迹信息(支持跨域)")
    @GetMapping(value = "")
    public ResponseEntity<QueryTrackResult> getExpressTrackSupportCrossOrigin(
            @RequestParam String com, @RequestParam String num) {
        ResponseEntity<QueryTrackResult> responseEntity;
        try {
            QueryTrackResult queryTrackResult = expressService.getExpressTrack(com, num);
            responseEntity = new ResponseEntity<>(queryTrackResult, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new QueryTrackResult(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}