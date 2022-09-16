package top.mxzero.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public class QQOAuthUtil {
    private QQOAuthUtil() {
    }

    /**
     * 根据 code 获取access_token
     */
    public static String getAccessToken(String url, String code, String clientId, String clientSecret, String redirectUrl) {
        CloseableHttpClient request = HttpClients.createDefault();
        String requestUrl = url
                + "?grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code
                + "&redirect_uri=" + redirectUrl;
        try (CloseableHttpResponse response = request.execute(new HttpGet(requestUrl))) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            String[] kvs = content.split("&");

            Map<String, String> data = new HashMap<>();

            for (String kv : kvs) {
                String[] split = kv.split("=");
                data.put(split[0], split[1]);
            }
            return data.get("access_token");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据access_token获取openid
     */
    public static String getOpenid(String url, String accessToken) {
        String requestUrl = url + "?fmt=json&access_token=" + accessToken;

        CloseableHttpClient request = HttpClients.createDefault();

        try (CloseableHttpResponse response = request.execute(new HttpGet(requestUrl))) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            Map map = JSONObject.parseObject(content, Map.class);
            return map.get("openid").toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据openid获取用户信息
     */
    public static Map<String, String> getUserInfo(String url, String accessToken, String openid, String clientKey) {

        String requestUrl = url
                + "?access_token=" + accessToken
                + "&oauth_consumer_key=" + clientKey
                + "&openid=" + openid;
        CloseableHttpClient request = HttpClients.createDefault();

        try (CloseableHttpResponse response = request.execute(new HttpGet(requestUrl))) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            return JSONObject.parseObject(content, Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
