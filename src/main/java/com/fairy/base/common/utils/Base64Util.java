package com.fairy.base.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Base64加密解密
 * @author lu
 * @version 1.0
 * @date  2022/4/2 20:59
 */
public class Base64Util {
    /**
     * 编码
     * @param str 字符串
     * @return String
     * @throws UnsupportedEncodingException 异常
     */
    public static String encode(final String str) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Base64.encodeBase64String(str.getBytes("utf-8"));
    }

    /**
     * 字节数组编码
     * @param binaryData 数组
     * @return String
     */
    public static String encode(byte[] binaryData) {
        return Base64.encodeBase64String(binaryData);
    }

    /**
     * 解码
     * @param strs 字符串
     * @return String
     */
    public static String decode(final String strs) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(strs)) {
            return null;
        }
        String str = new String(Base64.decodeBase64(strs), "UTF-8");
        return str;
    }

    /**
     * 解码
     * @param encodedText 字符串
     * @return byte
     */
    public static byte[] decodeBite(String encodedText) {
        byte[] bytes = Base64.decodeBase64(encodedText);
        return bytes;
    }

    /**
     * 解码
     * @param binaryData 字节数组
     * @return String
     * @throws UnsupportedEncodingException 异常
     */
    public static String decode(byte[] binaryData) throws UnsupportedEncodingException {
        String str = new String(Base64.decodeBase64(binaryData), "UTF-8");
        return str;
    }

    /**
     * 判断是否base64过的原文，原理为解密再加密，得到原字符串则代表的是已加密过的
     *
     * @param base64 字符串
     * @return boolean
     * @throws UnsupportedEncodingException 异常
     */
    public static boolean isBase64(final String base64) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(base64)) {
            return false;
        }
        String de = decode(base64);
        String en = encode(de).replaceAll("[\\s*\t\n\r]", "");
        return base64.equals(en);
    }


    public static long base64file_size(String base64String) {
        int size0 = base64String.length();
        //2.获取字符串的尾巴的最后10个字符，用于判断尾巴是否有等号，正常生成的base64文件'等号'不会超过4个
        String tail = base64String.substring(size0 - 10);
        //3.找到等号，把等号也去掉,(等号其实是空的意思,不能算在文件大小里面)
        int equalIndex = tail.indexOf("=");
        if (equalIndex > 0) {
            size0 = size0 - (10 - equalIndex);
        }
        //4.计算后得到的文件流大小，单位为字节
        return size0 - ((long) size0 / 8) * 2;
    }
}
