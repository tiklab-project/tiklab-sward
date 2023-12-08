package io.thoughtware.sward.starter.annotation;

import io.thoughtware.sward.starter.SwardAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SwardAutoConfiguration.class})
public @interface EnableKanass {
}
