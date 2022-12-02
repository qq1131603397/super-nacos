package com.hz.knife.config;

/**
 * @author： pt
 * @date： 2022/11/22 17:54
 * @discription
 */

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOpenApi
public class Knife4jConfiguration {
    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(true)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                //分组名称
                .groupName("default")
                //配置网关或者nginx
                //.pathMapping("knife")
                .select()
                //扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hz.knife.controller"))
                // 设置安全模式，swagger可以设置访问token
                //.securitySchemes(securitySchemes())
                //.securityContexts(securityContexts())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置标题
                .title("Knife接口文档")
                // 描述
                .description("Knife接口文档")
                // 作者信息
                .contact(new Contact("pt", null, null))
                // 版本
                .version("3.0.3").build();
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(o -> o.matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
