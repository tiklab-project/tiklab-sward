package com.doublekit.wiki.config;

import com.doublekit.eam.client.config.EamTicketConfig;
import com.doublekit.eam.client.config.EamTicketConfigBuilder;
import com.doublekit.eam.client.filter.EamTicketFilter;
import com.doublekit.eam.client.provider.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketFilterAutoConfiguration {

    @Autowired
    @Qualifier("AuthenticatorProxy")
    Authenticator authenticator;

    @Bean
    public FilterRegistrationBean ticketFilterRegistration(EamTicketConfig ticketConfig) {
        EamTicketFilter ticketFilter = new EamTicketFilter();
        ticketFilter.setTicketConfig(ticketConfig);
        ticketFilter.setAuthenticator(authenticator);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ticketFilter);
        registration.setName("ticketFilter");
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
