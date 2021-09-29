package com.doublekit.wiki.config;

import com.doublekit.gateway.core.config.RouterConfig;
import com.doublekit.gateway.core.config.RouterConfigBuilder;
import com.doublekit.gateway.core.filter.GatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayFilterAutoConfiguration {

    @Value("${gateway.project.address}")
    String projectAddress;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public FilterRegistrationBean gatewayFilterRegistration(RouterConfig routerConfig) {
        GatewayFilter gatewayFilter = new GatewayFilter();
        gatewayFilter.setRouterConfig(routerConfig);
        gatewayFilter.setRestTemplate(restTemplate);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(gatewayFilter);
        registration.setName("gatewayFilter");
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public RouterConfig routerConfig(){
        return RouterConfigBuilder.instance()
                .route("wiki",projectAddress)
                .get();
    }
}
