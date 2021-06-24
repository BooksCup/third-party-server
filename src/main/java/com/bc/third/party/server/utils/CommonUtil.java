package com.bc.third.party.server.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * 通用工具类
 *
 * @author zhou
 */
public class CommonUtil {

    /**
     * 生成主键
     *
     * @return 主键
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 列表转数组
     *
     * @param list 列表
     * @return 数组
     */
    public static String[] listToArray(List<String> list) {
        String[] array;
        if (!CollectionUtils.isEmpty(list)) {
            array = list.toArray(new String[list.size()]);
        } else {
            array = new String[]{};
        }
        return array;
    }

}