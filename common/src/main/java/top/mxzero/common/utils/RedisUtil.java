package top.mxzero.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @date 2022/9/15
 */
@Component
public class RedisUtil implements ApplicationContextAware {

    private static StringRedisTemplate stringRedisTemple;


    private RedisUtil() {
    }


    public static String get(String key) {
        return stringRedisTemple.opsForValue().get(key);
    }

    public static Long ttl(String key) {
        return stringRedisTemple.getExpire(key);
    }

    public static Long incr(String key) {
        return stringRedisTemple.opsForValue().increment(key);
    }

    public static Long incr(String key, Long step) {
        return stringRedisTemple.opsForValue().increment(key, step);
    }

    public static boolean del(String key) {
        return Boolean.TRUE.equals(stringRedisTemple.delete(key));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        stringRedisTemple = applicationContext.getBean(StringRedisTemplate.class);
    }
}
