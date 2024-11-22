package io.tiklab.sward.starter.config;


import io.tiklab.dfs.init.initdata.config.model.DfsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DfsInitAutoConfiguration {

    /**
     * 初始化dfs data
     */
    @Bean
    DfsConfig initDfsConfig() {
        DfsConfig dfsConfig = new DfsConfig()
            .newVersion("1.0.0","data_1.0.0")
                .newVersion("1.0.1","data_1.0.1");
        return dfsConfig;
    }
}