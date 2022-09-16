package top.mxzero.cloudstore.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Data
@ConfigurationProperties("mxzero.oauth2.qq")
public class OAuthInfoQQProperties {
    private String clientKey;
    private String clientSecret;
    private String redirectUrl;
    private String authorizationUrl;
    private String accessTokenUrl;
    private String openidUrl;
    private String userinfoUrl;
}
