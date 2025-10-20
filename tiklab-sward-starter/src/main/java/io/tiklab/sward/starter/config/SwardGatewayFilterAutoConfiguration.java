package io.tiklab.sward.starter.config;

import io.tiklab.gateway.config.GatewayConfig;
import io.tiklab.gateway.config.IgnoreConfig;
import io.tiklab.gateway.config.IgnoreConfigBuilder;
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
                        ".svg",
                        ".txt",
                        "docx",
                        "pdf",
                        "pptx",
                        "doc",
                        "xlsx",
                        "xls"
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
                        "/actuator/shutdown",
                        "/init/install/findStatus",
                        "/state/apply/findApply",
                        "/openapi/doc",
                        "/statisticsDoc/init",
                        "/user/user/recoverPassword",
                        "/wiki/kanass",
                        "/node/findNodeList",
                        "/document/download/file",
                        "/import/confluence/analysisEntityXml",
                        "/import/confluence/importJiraData",
                        "/documentExport/exportWord",
                        "/import/confluence/uploadFile",
                        "/import/confluence/analysisEntityXml",
                        "/import/confluence/importJiraData",
                        "/comment/findCommentTreePage",
                        "/onlyOffice/callback",
                        "/share/findShare",
                        "/repository/createRepositoryByValidCreateUser"
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
                        "/eam/wechat/passport",
                        "/ipRoll",
                        "/wiki/kanass",
                })
                .get();
    }
}
