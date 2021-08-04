package com.doublekit.wiki.annotation;

import com.doublekit.wiki.config.WikiServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({WikiServerAutoConfiguration.class})
public @interface EnableWikiServer {
}
