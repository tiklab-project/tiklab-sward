package io.tiklab.sward.starter.config;

import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.model.DsmVersion;
import io.tiklab.dsm.support.DsmVersionBuilder;
import io.tiklab.sward.support.task.service.*;
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

    @Autowired
    InitAuthority initAuthority;

    @Autowired
    InitMessageTask initMessageTask;

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

        DsmVersion sqlOnly = DsmVersionBuilder.instance()
                .version("sward-onlyOffice_1.0.0")
                .db(new String[]{
                        "sward-onlyOffice_1.0.0",
                }).get();
        versionList.add(sqlOnly);

        DsmVersion sqlShare = DsmVersionBuilder.instance()
                .version("sward-share_1.0.0")
                .db(new String[]{
                        "sward-share_1.0.0",
                }).get();
        versionList.add(sqlShare);

        DsmVersion sqlDocTep = DsmVersionBuilder.instance()
                .version("sward-doc_template_1.0.0")
                .db(new String[]{
                        "sward-doc_template_1.0.0",
                }).get();
        versionList.add(sqlDocTep);


        DsmVersion sql131 = DsmVersionBuilder.instance()
                .version("privilege-gorup_1.0.0")
                .db(new String[]{
                        "privilege-gorup_1.0.0",
                }).get();
        versionList.add(sql131);

        DsmVersion sql132 = DsmVersionBuilder.instance()
                .version("sward-new-pri-1.0.0")
                .db(new String[]{
                        "sward-new-pri-1.0.0",
                }).get();
        versionList.add(sql132);


        DsmVersion sql133 = DsmVersionBuilder.instance()
                .version("sward-node-add-permission_1.0.0")
                .db(new String[]{
                        "sward-node-add-permission_1.0.0",
                }).get();
        versionList.add(sql133);

        DsmVersion sql134 = DsmVersionBuilder.instance()
                .version("sward_2.0.0")
                .db(new String[]{
                        "sward_2.0.0",
                }).get();
        versionList.add(sql134);

        DsmVersion sql998 = DsmVersionBuilder.instance()
                .version("initAuthority")
                .task(initAuthority)
                .get();
        versionList.add(sql998);

        DsmVersion sql999 = DsmVersionBuilder.instance()
                .version("initMessageTask")
                .task(initMessageTask)
                .get();
        versionList.add(sql999);

        return versionList;
    }


}