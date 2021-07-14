package com.bc.third.party.server.mapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author zhou
 */
public interface CodeGeneratorMapper {

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
