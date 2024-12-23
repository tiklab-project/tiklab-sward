package io.tiklab.sward.starter.config;

import io.tiklab.eam.author.Authenticator;
import io.tiklab.eam.client.author.config.AuthorConfig;
import io.tiklab.eam.client.author.config.AuthorConfigBuilder;
import io.tiklab.eam.client.author.handler.AuthorHandler;
import io.tiklab.gateway.router.Router;
import io.tiklab.gateway.router.RouterBuilder;
import io.tiklab.gateway.router.config.RouterConfig;
import io.tiklab.gateway.router.config.RouterConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterAutoConfiguration {

    //网关filter
    @Value("${soular.address:null}")
    String EasAuthAddress;

    @Value("${soular.embbed.enable:false}")
    Boolean enableEam;

    @Bean
    Router router(RouterConfig routerConfig){
        return RouterBuilder.newRouter(routerConfig);
    }

    @Bean
    RouterConfig routerConfig(){
        String[] s = {};

        return RouterConfigBuilder.instance()
                .preRoute(s, EasAuthAddress)
                .get();
    }

    @Bean
    AuthorHandler authorFilter(Authenticator authenticator, AuthorConfig ignoreConfig){
        return new AuthorHandler()
                .setAuthenticator(authenticator)
                .setAuthorConfig(ignoreConfig);
    }


    @Bean
    public AuthorConfig authorConfig(){
        return AuthorConfigBuilder.instance()
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
                    "/init/install/findStatus"
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
                    "/dfs"
            })
            .get();
    }
}
