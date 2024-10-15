package io.tiklab.sward;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SwardServerAutoConfiguration.class})
public @interface EnableSwardServer {
}
