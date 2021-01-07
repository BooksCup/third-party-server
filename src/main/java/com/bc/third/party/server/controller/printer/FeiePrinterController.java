package com.bc.third.party.server.controller.printer;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.config.FeieConfig;
import com.bc.third.party.server.entity.printer.ContentDate;
import com.bc.third.party.server.entity.printer.Template1FormatData;
import com.bc.third.party.server.service.SystemConfigService;
import com.bc.third.party.server.utils.HttpUtil;
import com.bc.third.party.server.utils.TextForm;
import com.bc.third.party.server.utils.TextFormBulider;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.List;

/**
 * 飞鹅打印机
 *
 * @author zhou
 */
@RestController
@RequestMapping("/printer/feie")
public class FeiePrinterController {

    private static final Logger logger = LoggerFactory.getLogger(FeiePrinterController.class);

    private static final String URL = "http://api.feieyun.cn/Api/Open/";

    private static final int TEMPLATE1_Y_INCREMENT = 35;
    private static final int TEMPLATE1_BEGIN_Y = 50;

    @Resource
    private SystemConfigService systemConfigService;

    /**
     * 标签打印机打印订单(模板1)
     *
     * @param sn                 打印机编号
     * @param companyName        公司名
     * @param companyEnglishName 公司英文名
     * @param contents           打印内容(JSONArray)
     * @return ResponseEntity
     */
    @ApiOperation(value = "标签打印机打印订单(模板1)", notes = "标签打印机打印订单(模板1)")
    @PostMapping(value = "/template1")
    public ResponseEntity<String> printTemplate1(
            @RequestParam String sn,
            @RequestParam String companyName,
            @RequestParam String companyEnglishName,
            @RequestParam(required = false) String contents) {
        ResponseEntity<String> responseEntity;
        try {
            int y = TEMPLATE1_BEGIN_Y;
            List<ContentDate> contentDateList = JSON.parseArray(contents, ContentDate.class);

            SystemConfig systemConfig = systemConfigService.getSystemConfig(Constant.CONFIG_KEY_FEIE);
            FeieConfig feieConfig = JSON.parseObject(systemConfig.getValue(), FeieConfig.class);

            String user = feieConfig.getUser();
            String ukey = feieConfig.getKey();
            String stime = Long.toString(System.currentTimeMillis() / 1000L);
            String sig = DigestUtils.sha1Hex(user + ukey + stime);

            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("user", user);
            paramMap.put("stime", stime);
            paramMap.put("sig", sig);

            paramMap.put("apiname", "Open_printLabelMsg");
            paramMap.put("sn", sn);


            StringBuffer contentBuffer = new StringBuffer();
            contentBuffer.append("<TEXT x=\"" + 110 + "\" y=\"" + y + "\" font=\"12\" w=\"2\" h=\"2\">" + companyName + "</TEXT>");
            y += 70;
            contentBuffer.append("<TEXT x=\"" + 110 + "\" y=\"" + y + "\" font=\"12\" w=\"1\" h=\"1\">" + companyEnglishName + "</TEXT>");
            y += TEMPLATE1_Y_INCREMENT;
            TextFormBulider textFormBulider = TextForm.bulider();
            for (ContentDate contentDate : contentDateList) {
                textFormBulider.addRow(contentDate.getKey(), contentDate.getValue());
            }
            textFormBulider.addRow(" ", " ");
            Template1FormatData template1FormatData = textFormBulider
                    // 设置单元格最大数据长度
                    .colMaxLength(15)
                    // 设置表格由什么符号构成
                    .separator('|')
                    // 右边距
                    .paddingR(1)
                    // 左边距
                    .paddingL(1)
                    // 完成
                    .finish()
                    .getTableList();

            List<String> dataList = template1FormatData.getDataList();
            String separator = template1FormatData.getSeparator();
            String emptyDate = template1FormatData.getEmptyData();

            contentBuffer.append("<TEXT x=\"" + 12 + "\" y=\"" + y + "\" font=\"12\" w=\"1\" h=\"1\">" + separator + "</TEXT>");

            for (String table : dataList) {
                y += 30;
                contentBuffer.append("<TEXT x=\"" + 10 + "\" y=\"" + y + "\" font=\"12\" w=\"1\" h=\"1\">" + table + "</TEXT>");
                contentBuffer.append("<TEXT x=\"" + 10 + "\" y=\"" + (y - 15) + "\" font=\"12\" w=\"1\" h=\"1\">" + emptyDate + "</TEXT>");
                contentBuffer.append("<TEXT x=\"" + 10 + "\" y=\"" + (y + 10) + "\" font=\"12\" w=\"1\" h=\"1\">" + emptyDate + "</TEXT>");
                y += 25;
                contentBuffer.append("<TEXT x=\"" + 12 + "\" y=\"" + y + "\" font=\"12\" w=\"1\" h=\"1\">" + separator + "</TEXT>");
            }

            paramMap.put("content", contentBuffer.toString());
            logger.info("[printTemplate1], content.size: " + contentBuffer.length());
            paramMap.put("debug", "1");
            HttpUtil.doPost(URL, paramMap);
            responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
