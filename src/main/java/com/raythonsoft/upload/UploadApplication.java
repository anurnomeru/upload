package com.raythonsoft.upload;

import com.raythonsoft.upload.filter.ResourcesFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class UploadApplication {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    /**
     * 私有资源过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean resourcesFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ResourcesFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("ResourcesFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean //FIXME 允许所有跨域请求 需要研究一下
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}
