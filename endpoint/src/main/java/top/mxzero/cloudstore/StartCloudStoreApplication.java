package top.mxzero.cloudstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@SpringBootApplication
@ComponentScan("top.mxzero")
public class StartCloudStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartCloudStoreApplication.class, args);
    }
}
