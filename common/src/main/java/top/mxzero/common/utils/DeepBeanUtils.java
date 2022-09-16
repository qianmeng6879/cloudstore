package top.mxzero.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 拷贝集合Bean
 *
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public class DeepBeanUtils extends BeanUtils {
    private DeepBeanUtils() {
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T obj = target.get();
            copyProperties(source, obj);
            list.add(obj);
        }
        return list;
    }
}
