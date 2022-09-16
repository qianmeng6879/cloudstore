package top.mxzero.common.utils;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Component;
import top.mxzero.common.dto.MemberDTO;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Component
public class UserUtil {
    private static ThreadLocal<MemberDTO> threadLocal = new ThreadLocal<>();

    private UserUtil() {
    }

    public static MemberDTO currentUser() {
        return threadLocal.get();
    }

    public static void set(MemberDTO memberDTO) {
        threadLocal.set(memberDTO);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
