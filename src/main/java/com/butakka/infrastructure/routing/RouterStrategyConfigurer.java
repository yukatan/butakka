package com.butakka.infrastructure.routing;

import akka.actor.Props;

import java.lang.annotation.Annotation;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
public interface RouterStrategyConfigurer {

    Props configure(Props props,Annotation annotation);
}
