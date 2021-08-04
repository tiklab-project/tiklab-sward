package com.doublekit.wiki;

import com.doublekit.wiki.annotation.EnableWikiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * WikiApplication
 */
@SpringBootApplication
@PropertySource(value = "classpath:application-${env:local}.properties")
@EnableWikiServer
public class WikiApplication {

    public static final Logger logger = LoggerFactory.getLogger(WikiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WikiApplication.class, args);
    }

}
