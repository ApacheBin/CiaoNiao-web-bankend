package com.cainiaoshixi.util;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {

    /**
     * 默认随机码中包含的字符数
     */
    private static final int DEFAULT_CODE_NUM = 6;

    /**
     * 生成随机验证码
     */
    public static String generateVerificationCode() {
        return generateVerificationCode(DEFAULT_CODE_NUM);
    }

    public static String generateVerificationCode(int num) {
        char[] code = new char[num];
        for(int i = 0; i < num; i++) {
            code[i] = generateRandomNum();
        }
        return String.valueOf(code);
    }

    public static char generateRandomNum() {
        return generateRandomChar(0);
    }

    public static char generateRandomChar() {
        return generateRandomChar(3);
    }

    /**
     * 生成0-9，a-z, A-Z中的随机字符
     */
    public static char generateRandomChar(int bound) {
        Random random = new Random();
        if(0 < bound && bound < 4)
            switch (random.nextInt(bound)) {
                case 0: return (char)('0' + random.nextInt(10));
                case 1: return (char) ('a' + random.nextInt(26));
                default: return (char) ('A' + random.nextInt(26));
            }
        else
            return (char)('0' + random.nextInt(10));
    }
}
