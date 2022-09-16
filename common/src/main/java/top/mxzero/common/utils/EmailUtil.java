package top.mxzero.common.utils;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public class EmailUtil {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";


    public static boolean isEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        return email.matches(EMAIL_PATTERN);
    }
}
