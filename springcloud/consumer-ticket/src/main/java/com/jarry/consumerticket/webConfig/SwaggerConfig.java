package com.jarry.consumerticket.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.consumerticket.webConfig
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-17 16:26
 */
@Configuration
@EnableSwagger2
@Profile({"dev","test","prod"})
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jarry.consumerticket.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("营养探索馆-客服系统")
                .description("powered by By-Health")
                .termsOfServiceUrl("https://www.cnblogs.com/jiagod/")
                //.contact(contact)
                .version("1.0")
                .build();
    }

}
