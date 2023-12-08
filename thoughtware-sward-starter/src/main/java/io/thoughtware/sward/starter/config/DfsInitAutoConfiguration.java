package io.thoughtware.sward.starter.config;


import io.tiklab.dfs.client.initdata.config.model.DfsConfig;
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
                .newVersion("data_1.0.0");
        return dfsConfig;
    }
}