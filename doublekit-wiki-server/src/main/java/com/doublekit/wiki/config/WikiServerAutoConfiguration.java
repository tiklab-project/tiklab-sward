package com.doublekit.wiki.config;


import com.doublekit.datafly.starter.annotation.EnableDataFly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDataFly(location={
        "scripts/wiki.sql"
})
@ComponentScan({"com.doublekit.wiki"})
public class WikiServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(WikiServerAutoConfiguration.class);
}
