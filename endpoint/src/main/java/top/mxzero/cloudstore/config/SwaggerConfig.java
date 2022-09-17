package top.mxzero.cloudstore.config;

import org.assertj.core.internal.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/17
 */
@Controller
public class SwaggerConfig {
    @Bean
    public Docket coreApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.mxzero.cloudstore.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("CloudStore--API文档")
                .description("云存储API文档")
                .version("1.0")
                .contact(new Contact("zero","http://mxzero.top","qianmeng6879@163.com"))
                .build();
    }
}
