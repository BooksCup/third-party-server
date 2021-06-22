package com.bc.third.party.server.utils.test;

import com.bc.third.party.server.utils.BigDecimalUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 精确计算自测用例
 *
 * @author zhou
 */
public class BigDecimalUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(BigDecimalUtilTest.class);

    @Test
    public void testAdd() {

        BigDecimalUtil bigDecimalUtil = new BigDecimalUtil();
        BigDecimal result1 = bigDecimalUtil.add(1, 2, 3);
        logger.info("[add], result1: " + result1);
        Assert.assertEquals(result1, new BigDecimal("6"));

        BigDecimal result2 = bigDecimalUtil.add(null, 1);
        logger.info("[add], result2: " + result2);
        Assert.assertEquals(result2, new BigDecimal("1"));

        BigDecimal result3 = bigDecimalUtil.add(null, null, null, null);
        logger.info("[add], result3: " + result3);
        Assert.assertEquals(result3, new BigDecimal("0"));

        BigDecimal result4 = bigDecimalUtil.add("1.2213123123", null, null, "4213.11134123");
        logger.info("[add], result4: " + result4);
        Assert.assertEquals(result4, new BigDecimal("4214.3327"));

        BigDecimal result5 = bigDecimalUtil.add("中文", 1);
        logger.info("[add], result5: " + result5);
        Assert.assertEquals(result5, new BigDecimal("1"));

        BigDecimal result6 = bigDecimalUtil.add("中文", "1,3");
        logger.info("[add], result6: " + result6);
        Assert.assertEquals(result6, new BigDecimal("0"));

    }

    @Test
    public void testSubtract() {

        BigDecimalUtil util = new BigDecimalUtil();
        BigDecimal result1 = util.subtract(1, 2, 3);
        logger.info("[subtract], result1: " + result1);
        Assert.assertEquals(result1, new BigDecimal("-4"));

        BigDecimal result2 = util.subtract(null, 1);
        logger.info("[subtract], result2: " + result2);
        Assert.assertEquals(result2, new BigDecimal("-1"));

        BigDecimal result3 = util.subtract(null, null, null, null);
        logger.info("[subtract], result3: " + result3);
        Assert.assertEquals(result3, new BigDecimal("0"));

        BigDecimal result4 = util.subtract("1.2213123123", null, null, "4213.11134123");
        logger.info("[subtract], result4: " + result4);
        Assert.assertEquals(result4, new BigDecimal("-4211.89"));

        BigDecimal result5 = util.subtract("中文", 1);
        logger.info("[subtract], result5: " + result5);
        Assert.assertEquals(result5, new BigDecimal("-1"));

        BigDecimal result6 = util.subtract("中文", "1,3");
        logger.info("[subtract], result6: " + result6);
        Assert.assertEquals(result6, new BigDecimal("0"));

        BigDecimalUtil util2 = new BigDecimalUtil(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal result7 = util2.subtract("1.125", "2", "3");
        logger.info("[subtract], result7: " + result7);
        Assert.assertEquals(result7, new BigDecimal("-3.88"));

    }

    @Test
    public void testMultiply() {

        BigDecimalUtil util = new BigDecimalUtil();
        BigDecimal result1 = util.multiply(1, 2, 3);
        logger.info("[multiply], result1: " + result1);
        Assert.assertEquals(result1, new BigDecimal("6"));

        BigDecimal result2 = util.multiply(null, 1);
        logger.info("[multiply], result2: " + result2);
        Assert.assertEquals(result2, new BigDecimal("0"));

        BigDecimal result3 = util.multiply(null, null, null, null);
        logger.info("[multiply], result3: " + result3);
        Assert.assertEquals(result3, new BigDecimal("0"));

        BigDecimal result4 = util.multiply("1.2213123123", 1, 1, "4213.11134123");
        logger.info("[multiply], result4: " + result4);
        Assert.assertEquals(result4, new BigDecimal("5145.5248"));

        BigDecimal result5 = util.multiply("中文", 1);
        logger.info("[multiply], result5: " + result5);
        Assert.assertEquals(result5, new BigDecimal("0"));

        BigDecimal result6 = util.multiply("中文", "1,3");
        logger.info("[multiply], result6: " + result6);
        Assert.assertEquals(result6, new BigDecimal("0"));

        BigDecimalUtil util2 = new BigDecimalUtil(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal result7 = util2.multiply("1.125", "2", "3");
        logger.info("[multiply], result7: " + result7);
        Assert.assertEquals(result7, new BigDecimal("6.75"));

    }

    @Test
    public void testDivide() {

        BigDecimalUtil util = new BigDecimalUtil();
        BigDecimal result1 = util.divide(1, 2, 3);
        logger.info("[divide], result1: " + result1);
        Assert.assertEquals(result1, new BigDecimal("0.1667"));

        BigDecimal result2 = util.divide(null, 1);
        logger.info("[divide], result2: " + result2);
        Assert.assertEquals(result2, new BigDecimal("0"));

        BigDecimal result3 = util.divide(null, null, null, null);
        logger.info("[divide], result3: " + result3);
        Assert.assertEquals(result3, new BigDecimal("0"));

        BigDecimal result4 = util.divide("1.2213123123", null, null, "4213.11134123");
        logger.info("[divide], result4: " + result4);
        Assert.assertEquals(result4, new BigDecimal("0"));

        BigDecimal result5 = util.divide("中文", 1);
        logger.info("[divide], result5: " + result5);
        Assert.assertEquals(result5, new BigDecimal("0"));

        BigDecimal result6 = util.divide("中文", "1,3");
        logger.info("[divide], result6: " + result6);
        Assert.assertEquals(result6, new BigDecimal("0"));

        BigDecimalUtil util2 = new BigDecimalUtil(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal result7 = util2.divide("1", "2", "3");
        logger.info("[divide], result7: " + result7);
        Assert.assertEquals(result7, new BigDecimal("0.17"));

    }

}