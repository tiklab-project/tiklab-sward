package io.tiklab.sward.starter.config;

import io.tiklab.openapi.config.AllowConfig;
import io.tiklab.openapi.config.AllowConfigBuilder;
import io.tiklab.openapi.config.OpenApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwardOpenApiAutoConfiguration {

    @Bean
    OpenApiConfig openApiConfig(AllowConfig allowConfig){
        OpenApiConfig openApiConfig = new OpenApiConfig();
        openApiConfig.setAllowConfig(allowConfig);

        return openApiConfig;
    }

    //开放许可配置
    @Bean
    AllowConfig allowConfig(){
        String[] s =  new String[]{
                "/node/findNode",
                "/node/findNodePage",
                "/repository/findAllRepository",
                "/repository/findList",
                "/dmUser/findDmUserList",

                "/repository/updateRepository",
                "/repository/deleteRepository",
                "/repository/findRepository",
                "/repository/findAllRepository",
                "/repository/findRepositoryListByUser",
                "/repository/findRepositoryPage",
                "/repository/findRecentRepositoryList",
                "/repository/findFocusRepositoryList",
                "/repository/createRepository",
                "/repository/createRepositoryByValidCreateUser"
        };
        return AllowConfigBuilder.instance()
                .allowUrls(s)
                .get();
    }
}
