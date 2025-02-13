package io.tiklab.sward.starter;

import io.tiklab.dfs.boot.starter.annotation.EnableDfsInit;
import io.tiklab.dsm.boot.starter.annotation.EnableDsm;
import io.tiklab.dss.boot.starter.annotation.EnableDssInit;
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
import io.tiklab.postgresql.EnablePostgresql;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.annotation.EnableRpc;
import io.tiklab.security.boot.stater.annotation.EnableSecurityServer;
import io.tiklab.toolkit.boot.starter.annotation.EnableToolkit;
import io.tiklab.user.boot.starter.annotation.EnableUserClient;
import io.tiklab.user.boot.starter.annotation.EnableUserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
@EnablePostInClient
@EnableOpenApi
@ComponentScan({"io.tiklab.sward"})
public class SwardAutoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(SwardAutoConfiguration.class);
}
