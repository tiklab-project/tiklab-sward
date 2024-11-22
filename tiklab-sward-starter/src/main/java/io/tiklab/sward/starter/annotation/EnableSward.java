package io.tiklab.sward.starter.annotation;

import io.tiklab.sward.starter.SwardAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SwardAutoConfiguration.class})
public @interface EnableSward {
}
