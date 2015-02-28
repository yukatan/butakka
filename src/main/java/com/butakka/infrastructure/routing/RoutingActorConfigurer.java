package com.butakka.infrastructure.routing;

import akka.actor.Props;
import akka.routing.NoRouter;
import com.butakka.annotations.Configurer;
import com.butakka.annotations.RouterStrategy;
import com.butakka.annotations.WithRouter;
import com.butakka.infrastructure.configurer.ActorConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@Configurer
public class RoutingActorConfigurer implements ActorConfigurer {

    @Autowired
    private ApplicationContext context;

    private Map<Class,RouterStrategyConfigurer> routersConfigurers = new HashMap<Class, RouterStrategyConfigurer>();

    @Override
    public Props configure(Class actorType,Props props) {

        WithRouter annotation = AnnotationUtils.findAnnotation(actorType,WithRouter.class);
        return routersConfigurers.get(annotation.value()).configure(props,annotation);

    }

    @Override
    public boolean isConfigurableBy(Class actorType) {

        boolean isAnnotated =  AnnotationUtils.isAnnotationDeclaredLocally(WithRouter.class, actorType);
        if(!isAnnotated)
            return false;
        return !AnnotationUtils.findAnnotation(actorType,WithRouter.class).value().equals(NoRouter.class);
    }


    @PostConstruct
    public void getRouterStrategies(){

        Map <String,Object> configurers  = context.getBeansWithAnnotation(RouterStrategy.class);
        for(Object configurer: configurers.values()){

            RouterStrategy strategy = AnnotationUtils.findAnnotation(configurer.getClass(),RouterStrategy.class);
            routersConfigurers.put(strategy.value(),(RouterStrategyConfigurer) configurer);
        }
    }


}
