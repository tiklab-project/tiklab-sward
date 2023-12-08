package io.thoughtware.sward.starter.config;


import io.tiklab.dss.client.initdata.config.model.DssConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DssInitAutoConfiguration {

    /**
     * 初始化dss data
     */
    @Bean
    DssConfig initDssConfig() {
        DssConfig dssConfig = new DssConfig()
                .newVersion("1.0.0")
                .newVersion("1.0.1")
                .newVersion("1.0.2");
        return dssConfig;
    }
}