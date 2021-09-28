package com.doublekit.wiki.config;

import com.doublekit.eam.authenticator.config.EamTicketConfig;
import com.doublekit.eam.authenticator.config.EamTicketConfigBuilder;
import com.doublekit.eam.authenticator.filter.EamTicketFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketFilterAutoConfiguration {

    @Bean
    public FilterRegistrationBean ticketConfig(EamTicketConfig ticketConfig) {
        EamTicketFilter ticketFilter = new EamTicketFilter();
        ticketFilter.setTicketConfig(ticketConfig);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ticketFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public EamTicketConfig ticketConfig(){
        return EamTicketConfigBuilder.instance()
                .ignoreTypes(new String[]{
                        ".ico",
                        ".jpg",
                        ".jpeg",
                        ".png",
                        ".gif",
                        ".html",
                        ".js",
                        ".css",
                        ".json",
                        ".xml",
                        ".ftl"
                })
                .ignoreUrls(new String[]{
                        "/",
                        "/passport/login",
                        "/passport/logout",
                        "/passport/valid",
                        "/auth/valid",
                        "/document/view",
                        "/comment/view",
                        "/share/verifyAuthCode",
                        "/share/judgeAuthCode"
                })
                .ignorePreUrls(new String[]{
                        "/apis/list",
                        "/apis/detail",
                        "/file",
                        "/plugin",
                        "/authConfig",
                        "/app"
                })
                .get();
    }
}
