package top.mxzero.common.utils;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public class MobileUtil {
    private static final String MOBILE_PATTERN = "1[3-9]\\d{9}";

    private MobileUtil() {
    }

    /**
     * 检验手机号码格式是否正确
     */
    public static boolean isMobile(String mobile) {
        return mobile.matches(MOBILE_PATTERN);
    }

    public static String desensitization(String mobile) {
        if (!isMobile(mobile)) {
            return mobile;
        }
        return String.format("%s*******%s", mobile.substring(0, 2), mobile.substring(9, 11));
    }

}
