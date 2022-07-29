package com.tiklab.kanass;


import com.tiklab.dsm.annotation.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SQL(modules = "kanass")
@ComponentScan({"com.tiklab.kanass"})
public class KanassServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(KanassServerAutoConfiguration.class);
}
