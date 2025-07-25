package io.tiklab.sward.starter.config;

import io.tiklab.dfs.boot.starter.annotation.EnableDfsInit;
import io.tiklab.dsm.boot.starter.annotation.EnableDsm;
import io.tiklab.dss.boot.starter.annotation.EnableDssInit;
import io.tiklab.install.spring.boot.starter.EnableInstallServer;
import io.tiklab.postgresql.spring.boot.starter.EnablePostgresql;
import io.tiklab.postin.client.openapi.ParamConfig;
import io.tiklab.postin.client.openapi.ParamConfigBuilder;
import io.tiklab.postin.client.openapi.PostInClientConfig;
import io.tiklab.security.backups.config.BackupsConfig;
import io.tiklab.sward.EnableSwardServer;
import io.tiklab.dal.boot.starter.annotation.EnableDal;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsClient;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsServer;
import io.tiklab.dfs.boot.starter.annotation.EnableDfsClient;
import io.tiklab.dfs.boot.starter.annotation.EnableDfsServer;
import io.tiklab.dss.boot.starter.annotation.EnableDssClient;
import io.tiklab.dss.boot.starter.annotation.EnableDssServer;
import io.tiklab.eam.boot.starter.annotation.EnableEamClient;
import io.tiklab.eam.boot.starter.annotation.EnableEamServer;
import io.tiklab.gateway.boot.starter.annotation.EnableGateway;
import io.tiklab.licence.boot.starter.annotation.EnableLicenceServer;
import io.tiklab.messsage.boot.starter.annotation.EnableMessageServer;
import io.tiklab.openapi.boot.starter.annotation.EnableOpenApi;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.annotation.EnableRpc;
import io.tiklab.security.boot.stater.annotation.EnableSecurityServer;
import io.tiklab.toolkit.boot.starter.annotation.EnableToolkit;
import io.tiklab.user.boot.starter.annotation.EnableUserClient;
import io.tiklab.user.boot.starter.annotation.EnableUserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

import java.util.HashMap;

import static io.tiklab.security.backups.util.BackupsFinal.DFS;

@Configuration
@EnableToolkit
@EnableDal
@EnableDsm
@EnableDfsClient
@EnableDfsServer
@EnableDfsInit
@EnableDssClient
@EnableDssServer
@EnableDcsClient
@EnableDssInit
@EnableDcsServer
@EnableRpc
@EnableGateway
@EnableInstallServer
@EnablePostgresql
//pcs
@EnableEamClient
@EnableEamServer
@EnableUserClient
@EnableUserServer
@EnablePrivilegeServer
@EnableLicenceServer
@EnableMessageServer
@EnableSecurityServer
//other
@EnableSwardServer
// postin
@EnablePostInClient
@EnableOpenApi
@ComponentScan({"io.tiklab.sward"})

public class SwardAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(SwardAutoConfiguration.class);


    @Value("${DATA_HOME}")
    private String dataHome;

    @Bean
    BackupsConfig backupsConfig() {
        return BackupsConfig.instance()
                .addPath(DFS,dataHome+"/files")
                .get();
    }

    @Bean
    public MultipartConfigElement multipartConfig() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件大小5GB
        factory.setMaxFileSize(DataSize.ofGigabytes(5L));
        //设置总上传数据大小5GB
        factory.setMaxRequestSize(DataSize.ofGigabytes(5L));

        return factory.createMultipartConfig();
    }


    @Bean
    PostInClientConfig postInClientConfig(ParamConfig paramConfig){
        PostInClientConfig config = new PostInClientConfig();
        config.setParamConfig(paramConfig);

        return config;
    }

    @Bean
    ParamConfig paramConfig(){
        //设置请求头，属性名称：属性描述
        HashMap<String,String> headers = new HashMap<>();
        headers.put("accessToken","openApi中添加accessToken");

        return ParamConfigBuilder.instance()
                .setScanPackage("io.tiklab.sward") //设置扫描的包路径
                .prePath("/api")             //设置额外的前缀
                .setHeaders(headers)               //设置请求头
                .get();
    }

}




