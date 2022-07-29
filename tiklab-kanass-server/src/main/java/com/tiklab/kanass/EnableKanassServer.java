package com.tiklab.kanass;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({KanassServerAutoConfiguration.class})
public @interface EnableKanassServer {
}
