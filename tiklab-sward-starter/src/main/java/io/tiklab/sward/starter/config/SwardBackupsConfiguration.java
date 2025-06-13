package io.tiklab.sward.starter.config;

import io.tiklab.security.backups.config.BackupsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static io.tiklab.security.backups.util.BackupsFinal.*;

@Configuration
public class SwardBackupsConfiguration {


    @Value("${DATA_HOME}")
    private String dataHome;

    @Bean
    BackupsConfig backupsConfig() {
        return BackupsConfig.instance()
                .addPath(DFS,dataHome+"/files")
                .get();
    }

}