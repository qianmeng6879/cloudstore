package top.mxzero.cloudstore.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.mxzero.cloudstore.prop.OAuthInfoQQProperties;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Configuration
@EnableConfigurationProperties(OAuthInfoQQProperties.class)
public class PropertiesConfig {
}
