package com.butakka.annotations;

import akka.routing.NoRouter;
import akka.routing.RouterConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jefe on 22/02/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithRouter {

    Class<? extends RouterConfig> value() default NoRouter.class;
}
