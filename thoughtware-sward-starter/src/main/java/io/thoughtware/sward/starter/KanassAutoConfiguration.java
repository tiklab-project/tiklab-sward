package io.thoughtware.sward.starter;

import io.thoughtware.sward.EnableKanassServer;
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
import io.tiklab.messsage.boot.starter.annotation.EnableMessageClient;
import io.tiklab.messsage.boot.starter.annotation.EnableMessageServer;
import io.tiklab.openapi.boot.starter.annotation.EnableOpenApi;
import io.tiklab.plugin.starter.EnablePluginServer;
import io.tiklab.postgresql.EnablePostgresql;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.annotation.EnableRpc;
import io.tiklab.security.boot.stater.annotation.EnableSecurityClient;
import io.tiklab.security.boot.stater.annotation.EnableSecurityServer;
import io.tiklab.todotask.boot.stater.annotation.EnableTodoTaskClient;
import io.tiklab.todotask.boot.stater.annotation.EnableTodoTaskServer;
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
@EnableDfsClient
@EnableDfsServer
@EnableDssClient
@EnableDssServer
@EnableDcsClient
@EnableDcsServer
@EnableRpc
@EnableGateway
@EnablePostgresql
//pcs
@EnableEamClient
@EnableEamServer
@EnableUserClient
@EnableUserServer
@EnablePluginServer
@EnablePrivilegeServer
@EnableLicenceServer
@EnableMessageServer
@EnableMessageClient
@EnableSecurityServer
@EnableSecurityClient
@EnableTodoTaskServer
@EnableTodoTaskClient
//other
@EnableKanassServer
@EnablePostInClient
@EnableOpenApi
@ComponentScan({"io.tiklab.sward"})
public class KanassAutoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(KanassAutoConfiguration.class);
}
