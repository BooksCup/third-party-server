package com.bc.third.party.server.controller.code;

import com.bc.third.party.server.service.CodeGeneratorService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 代码生成器
 *
 * @author zhou
 */
@RestController
@RequestMapping("/codeGenerator")
public class CodeGeneratorController {
    @Resource
    private CodeGeneratorService codeGeneratorService;

    @ApiOperation(value = "", notes = "")
    @GetMapping(value = "")
    public void generatorCode(HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{"t_crawler_header"};
        byte[] data = codeGeneratorService.generatorCode(tableNames, "SmsLog", "com.bc.third.party.server", "zhou");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generator-code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}