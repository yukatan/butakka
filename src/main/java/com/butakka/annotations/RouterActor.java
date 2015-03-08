package com.butakka.annotations;

import akka.routing.NoRouter;
import akka.routing.RouterConfig;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope("prototype")
@Lazy
public @interface RouterActor {

    String actorId();
    Class<? extends RouterConfig> value() default NoRouter.class;
    int instances() default 1;

}

