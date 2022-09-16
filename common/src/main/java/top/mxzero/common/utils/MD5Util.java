package top.mxzero.common.utils;

import java.security.MessageDigest;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @date 2022/9/15
 */
public class MD5Util {
    static char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    private MD5Util() {
    }

    public static String md5(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(value.getBytes());
            return byte2str(messageDigest.digest());
        } catch (Exception e) {
            return null;
        }
    }

    private static String byte2str(byte []bytes){
        StringBuilder result = new StringBuilder();
        for (byte byte0 : bytes) {
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }
}
