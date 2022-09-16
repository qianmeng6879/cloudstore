package top.mxzero.cloudstore.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Configuration
@MapperScan("top.mxzero.cloudstore.mapper")
public class MybatisPlusConfig {

}
