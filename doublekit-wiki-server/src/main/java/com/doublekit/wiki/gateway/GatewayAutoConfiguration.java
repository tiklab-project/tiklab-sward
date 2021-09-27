package com.doublekit.wiki.gateway;

import com.doublekit.gateway.core.config.RouterConfig;
import com.doublekit.gateway.core.config.RouterConfigBuilder;
import com.doublekit.gateway.core.filter.GatewayFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayAutoConfiguration {

    @Value("${gateway.project.address}")
    String projectAddress;

    @Bean
    public FilterRegistrationBean gatewayFilter(RestTemplate restTemplate,RouterConfig routerConfig) {
        GatewayFilter gatewayFilter = new GatewayFilter();
        gatewayFilter.setRestTemplate(restTemplate);
        gatewayFilter.setRouterConfig(routerConfig);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(gatewayFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public RouterConfig routerConfig(){
        return RouterConfigBuilder.instance()
                .route("project",projectAddress)
                .get();
    }
}
