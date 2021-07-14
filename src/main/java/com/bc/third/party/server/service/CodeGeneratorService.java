package com.bc.third.party.server.service;

/**
 * 代码生成器
 *
 * @author zhou
 */
public interface CodeGeneratorService {

    byte[] generatorCode(String[] tableNames, String moduleName, String packageName, String author);

}