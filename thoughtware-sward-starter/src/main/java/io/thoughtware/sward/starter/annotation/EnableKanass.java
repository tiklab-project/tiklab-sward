package io.thoughtware.sward.starter.annotation;

import io.thoughtware.sward.starter.KanassAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({KanassAutoConfiguration.class})
public @interface EnableKanass {
}
