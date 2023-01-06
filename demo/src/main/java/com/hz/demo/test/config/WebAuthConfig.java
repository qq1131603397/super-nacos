package com.hz.demo.test.config;

import com.hz.demo.test.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author： pt
 * @date： 2023/1/5 14:56
 * @discription
 */
@Configuration
public class WebAuthConfig extends WebMvcConfigurationSupport {

    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor());
        super.addInterceptors(registry);
    }

}
