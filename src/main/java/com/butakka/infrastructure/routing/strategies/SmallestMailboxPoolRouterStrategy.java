package com.butakka.infrastructure.routing.strategies;

import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.routing.Pool;
import akka.routing.SmallestMailboxPool;
import com.butakka.annotations.RouterStrategy;
import com.butakka.annotations.WithRouter;
import com.butakka.infrastructure.routing.RouterStrategyConfigurer;
import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@RouterStrategy(SmallestMailboxPool.class)
public class SmallestMailboxPoolRouterStrategy implements RouterStrategyConfigurer {

    @Override
    public Props configure(Props props,Annotation annotation) {

        WithRouter withRouterAnnotation = (WithRouter) annotation;
        Pool routerConfig = new SmallestMailboxPool(withRouterAnnotation.instances());
        return props.withRouter(routerConfig);


    }
}
