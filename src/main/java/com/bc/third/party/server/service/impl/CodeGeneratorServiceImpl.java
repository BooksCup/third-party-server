package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.mapper.CodeGeneratorMapper;
import com.bc.third.party.server.service.CodeGeneratorService;
import com.bc.third.party.server.utils.CodeGenUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author zhou
 */
@Service("codeGeneratorService")
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Resource
    CodeGeneratorMapper codeGeneratorMapper;

    private Map<String, String> queryTable(String tableName) {
        return codeGeneratorMapper.queryTable(tableName);
    }

    private List<Map<String, String>> queryColumns(String tableName) {
        return codeGeneratorMapper.queryColumns(tableName);
    }

    public byte[] generatorCode(String[] tableNames, String moduleName, String packageName, String author){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames){
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            CodeGenUtil.generatorCode(table, moduleName, packageName, author, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}