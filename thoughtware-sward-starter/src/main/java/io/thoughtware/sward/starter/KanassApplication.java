package io.thoughtware.sward.starter;

import io.thoughtware.sward.starter.annotation.EnableKanass;
import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * WikiApplication
 */
@SpringBootApplication
@PropertySource(value = "classpath:application.yaml", factory = PropertyAndYamlSourceFactory.class)
@EnableKanass
public class KanassApplication {

    public static final Logger logger = LoggerFactory.getLogger(KanassApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KanassApplication.class, args);
    }

}