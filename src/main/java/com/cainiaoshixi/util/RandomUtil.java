package com.cainiaoshixi.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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

    public static void generateQRcode( String content, OutputStream out) throws WriterException, IOException {
        int width = 150;
        int height = 150;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(matrix, "png", out);
    }
}
