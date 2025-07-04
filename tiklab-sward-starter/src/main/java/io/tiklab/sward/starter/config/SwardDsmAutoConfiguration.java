package io.tiklab.sward.starter.config;

import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.model.DsmVersion;
import io.tiklab.dsm.support.DsmVersionBuilder;
import io.tiklab.sward.support.support.DssInitIndex;
import io.tiklab.sward.support.util.task.TaskInitDss;
import io.tiklab.sward.support.util.task.TaskPrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwardDsmAutoConfiguration {


    @Autowired
    TaskInitDss taskInitDss;

    @Autowired
    TaskPrivilege privilege;

    @Autowired
    DssInitIndex dssInitIndex;

    @Bean
    DsmConfig dsmConfig(){
        DsmConfig dsmConfig = new DsmConfig();

        dsmConfig.setVersionList(initDsmConfig());
        return dsmConfig;
    }


    @Bean
    List<DsmVersion> initDsmConfig() {

        List<DsmVersion> versionList = new ArrayList<>();

        //1.0.0
        DsmVersion dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.0")
                .db(new String[]{
                    "user_1.0.0",
                    "privilege_1.0.0",
                    "app-authorization_1.0.0",
                    "message_1.0.0",
                    "oplog_1.0.0",
                    "todotask_1.0.0",
                    "doc_1.0.0",
                })
                .dfs(new String[]{"data_1.0.0"})
                .task(dssInitIndex)
                .get();
        versionList.add(dsmVersion);

        DsmVersion dsmVersionDss = DsmVersionBuilder.instance()
                .version("sward_dss").db( new String[]{})
                .task(taskInitDss)
                .get();
        versionList.add(dsmVersionDss);

        DsmVersion dsmVersionPrivilege = DsmVersionBuilder.instance()
                .version("sward_privilege").db( new String[]{})
                .task(privilege)
                .get();
        versionList.add(dsmVersionPrivilege);

        DsmVersion message_109 = DsmVersionBuilder.instance()
                .version("message_1.0.9")
                .db(new String[]{
                        "message_1.0.9",
                }).get();
        versionList.add(message_109);

        return versionList;
    }


}