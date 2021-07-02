package com.bc.third.party.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author zhou
 */
public class TimeUtil {

    /**
     * 格式化发送时间
     *
     * @param time 发送时间
     * @return 发送日期
     */
    public static String formatSendTime(String time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = timeFormat.parse(time);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}