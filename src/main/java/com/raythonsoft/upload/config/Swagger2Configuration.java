package com.raythonsoft.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  Swagger2 配置
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class Swagger2Configuration {

    @Bean
    public Docket createRest4web() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("raythonsoft")
                .apiInfo(apiInfo4web())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.raythonsoft.upload.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo4web() {
        return new ApiInfoBuilder()
                .title("swagger")
                .description("web端统一接口")
                .termsOfServiceUrl("http://www.raythonsoft.com")
                .contact("neal@raythonsoft")
                .version("1.0")
                .build();
    }
}
