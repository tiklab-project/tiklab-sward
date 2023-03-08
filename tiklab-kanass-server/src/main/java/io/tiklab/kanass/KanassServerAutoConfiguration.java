package io.tiklab.kanass;

import io.tiklab.dsm.model.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.tiklab.kanass"})
public class KanassServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(KanassServerAutoConfiguration.class);
    @Bean
    SQL initSQL(){
        return new SQL(new String[]{
                "kanass"
        },101);
    }
}
