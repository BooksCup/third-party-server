package com.bc.third.party.server.cons;

/**
 * 常量类
 *
 * @author zhou
 */
public class Constant {

    /**
     * 初始化hashMap容量
     */
    public static final int DEFAULT_HASH_MAP_CAPACITY = 16;

    public static final String NOTIFY_TYPE_SMS = "SMS";

    // 短信回执处理状态
    /**
     * 短信回执处理状态 - 待处理
     */
    public static final String SMS_RESPONSE_STATE_PENDING = "0";

    /**
     * 短信回执处理状态 - 已处理
     */
    public static final String SMS_RESPONSE_STATE_SOLVED = "1";

    // 定时任务业务类型
    /**
     * 定时任务 - 获取短信发送记录和发送状态
     */
    public static final String CRON_SERVICE_TYPE_QUERY_SMS_SEND_DETAILS = "0";

    // 默认页数和默认分页数
    /**
     * 默认当前页数
     */
    public static final String DEFAULT_PAGE = "1";

    /**
     * 默认分页大小
     */
    public static final String DEFAULT_PAGE_SIZE = "10";

}