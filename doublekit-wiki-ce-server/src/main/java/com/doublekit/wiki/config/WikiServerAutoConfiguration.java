package com.doublekit.wiki.config;


import com.doublekit.apibox.client.annotation.EnableApiboxClient;
import com.doublekit.datafly.starter.annotation.EnableDatafly;
import com.doublekit.flow.annotation.EnableFlowServer;
import com.doublekit.form.annotation.EnableFormServer;
import com.doublekit.message.annotation.EnableMessageServer;
import com.doublekit.plugin.annotation.EnablePluginServer;
import com.doublekit.privilege.annotation.EnablePrivilegeServer;
import com.doublekit.toolkit.annotation.EnableToolkitServer;
import com.doublekit.user.annotation.EnableUserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableUserServer
@EnablePrivilegeServer
@EnableFormServer
@EnableFlowServer
@EnableMessageServer
@EnablePluginServer
@EnableToolkitServer
@EnableApiboxClient
@EnableDatafly(location={
        "scripts/wiki.sql"
})
@ComponentScan({"com.doublekit.wiki"})
public class WikiServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(WikiServerAutoConfiguration.class);
}
