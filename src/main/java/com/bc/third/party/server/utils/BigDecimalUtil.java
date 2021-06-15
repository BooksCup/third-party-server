package com.bc.third.party.server.utils;

import java.math.BigDecimal;

/**
 * 精确计算
 * 注意: 使用参数为float或double的BigDecimal创建对象会丢失精度
 * 因此强烈建议不要使用参数为float或double的BigDecimal创建对象
 *
 * @author zhou
 */
public class BigDecimalUtil {

    /**
     * 保留小数位
     */
    private int scale;

    /**
     * 小数舍入模式
     */
    private int roundingMode;

    public BigDecimalUtil() {
        this.scale = 4;
        this.roundingMode = BigDecimal.ROUND_HALF_UP;
    }

    public BigDecimalUtil(int scale, int roundingMode) {
        this.scale = scale;
        this.roundingMode = roundingMode;
    }

    /**
     * 对多个数求和，至少两个数
     * 非数字参数当0处理
     *
     * @param number1 第一个数
     * @param number2 第二个数
     * @param numberN 第N个数
     * @return 和
     */
    public BigDecimal add(Object number1, Object number2, Object... numberN) {
        BigDecimal bigDecimal1 = object2BigDecimal(number1);
        BigDecimal bigDecimal2 = object2BigDecimal(number2);
        BigDecimal result = bigDecimal1.add(bigDecimal2);
        for (Object number : numberN) {
            BigDecimal bigDecimal = object2BigDecimal(number);
            result = result.add(bigDecimal);
        }
        return result.setScale(scale, roundingMode).stripTrailingZeros();
    }

    /**
     * 多个数相除，至少两个数
     *
     * @param number1 被除数
     * @param number2 第一个除数
     * @param numberN 第（N-1）个除数
     * @return 余数
     */
    public BigDecimal divide(Object number1, Object number2, Object... numberN) {
        BigDecimal result;
        try {
            BigDecimal bigDecimal1 = object2BigDecimal(number1);
            BigDecimal bigDecimal2 = object2BigDecimal(number2);
            result = bigDecimal1.divide(bigDecimal2, scale, roundingMode);
            for (Object number : numberN) {
                BigDecimal bigDecimal = object2BigDecimal(number);
                result = result.divide(bigDecimal, scale, roundingMode);
            }
        } catch (ArithmeticException e) {
            result = new BigDecimal("0");
        }
        return result.stripTrailingZeros();
    }

    /**
     * 数字对象转BigDecimal
     *
     * @param number 数字对象
     * @return BigDecimal
     */
    private BigDecimal object2BigDecimal(Object number) {
        BigDecimal bigDecimal;
        try {
            bigDecimal = new BigDecimal(number.toString());
        } catch (Exception e) {
            bigDecimal = new BigDecimal("0");
        }
        return bigDecimal;
    }

}