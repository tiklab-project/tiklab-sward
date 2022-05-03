package com.doublekit.wiki;


import com.doublekit.dsm.annotation.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SQL(modules = "wiki")
@ComponentScan({"com.doublekit.wiki"})
public class WikiServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(WikiServerAutoConfiguration.class);
}
