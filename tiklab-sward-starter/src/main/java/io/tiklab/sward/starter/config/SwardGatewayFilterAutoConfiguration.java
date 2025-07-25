package io.tiklab.sward.starter.config;

import io.tiklab.eam.author.Authenticator;
import io.tiklab.eam.client.author.handler.DefaultAuthorHandler;
import io.tiklab.gateway.config.GatewayConfig;
import io.tiklab.gateway.config.IgnoreConfig;
import io.tiklab.gateway.config.IgnoreConfigBuilder;
import io.tiklab.gateway.handler.author.AuthorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwardGatewayFilterAutoConfiguration {


    @Bean
    GatewayConfig gatewayConfig(IgnoreConfig ignoreConfig){
        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setIgnoreConfig(ignoreConfig);

        return gatewayConfig;
    }


    @Bean
    public IgnoreConfig authorConfig(){
        return IgnoreConfigBuilder.instance()
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
                    ".ftl",
                    ".svg"
            })
            .ignoreUrls(new String[]{
                    "/",
                    "/eam",
                    "/passport/login",
                    "/passport/logout",
                    "/passport/valid",
                    "/auth/valid",
                    "/document/view",
                    "/comment/view",
                    "/share/verifyAuthCode",
                    "/share/judgeAuthCode",
                    "/version/getVersion",
                    "/eam/auth/login",
                    "/category/findCategory",
                    "/category/findCategoryDocument",
                    "/permission/findPermissions",
                    "/updateMySql/updateAllData",
                    "/message/messageItem/syncUpdateMessage",
                    "/message/messageItem/syncDeleteMessage",
                    "/init/install/findStatus",
                    "/openapi/doc",
                    "/actuator/shutdown",
                    "/wiki/kanass",
                    "/node/findNodeList",
                    "/import/confluence/uploadFile",
                    "/import/confluence/analysisEntityXml",
                    "/import/confluence/importJiraData",
                    "/node/findNodeList"
            })
            .ignorePreUrls(new String[]{
                    "/apis/list",
                    "/apis/detail",
                    "/file",
                    "/plugin",
                    "/authConfig",
                    "/app",
                    "/licence",
                    "/workLog",
                    "/share",
                    "/service",
                    "/wikiProject",
                    "/systemUrl",
                    "/image",
                    "/dfs",
                    "/wiki/kanass",
                    "/document/download/file",
            })
            .get();
    }
}
