package io.tiklab.sward.starter;

import io.tiklab.sward.starter.annotation.EnableSward;
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
@EnableSward
public class SwardApplication {

    public static final Logger logger = LoggerFactory.getLogger(SwardApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwardApplication.class, args);
    }

}
