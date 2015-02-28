package com.butakka.infrastructure.context;

import akka.actor.*;
import com.butakka.annotations.AkkaActor;
import com.butakka.error.ActorTypeNotFoundException;
import com.butakka.infrastructure.configurer.ActorConfigurer;
import com.butakka.infrastructure.extension.SpringExtension;
import com.butakka.infrastructure.routing.RoutingActorConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesús Barquín on 21/02/15.
 */
public class DefaultAkkaContext implements AkkaContext {

    @Autowired
    private ActorSystem system;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private RoutingActorConfigurer configurer;

    private Map<Class,Props> actorProducers = new HashMap<Class, Props>();

    @PostConstruct
    public void processActors(){

        String[] actorNames = context.getBeanNamesForAnnotation(AkkaActor.class);
        for(String name : actorNames){

            Class actorType = context.getType(name);
            Props producer = SpringExtension.SpringExtProvider.get(system).props(actorType);
            if(configurer.isConfigurableBy(actorType))
                producer = configurer.configure(actorType,producer);
            actorProducers.put(actorType, producer);
        }
    }

    @Override
    public ActorRef getActorRef(Class actorType){

        Props props = actorProducers.get(actorType);
        return system.actorOf(props);

    }

    @Override
    public ActorRef getActorRef(Class actorType, String name){

        Props props = actorProducers.get(actorType);
        if(props == null)
            throw new ActorTypeNotFoundException(actorType.getCanonicalName());
        return system.actorOf(props,name);
    }
    @Override
    public ActorRef getActorRef(Class actorType, UntypedActorContext context){

        Props props = actorProducers.get(actorType);
        return context.actorOf(props);
    }

    @Override
    public ActorRef getActorRef(Class actorType, UntypedActorContext context, String name){

        Props props = actorProducers.get(actorType);
        return system.actorOf(props,name);
    }

    @Override
    public ActorSystem getSystem() {
        return system;
    }

    @Override
    public Props getActorProps(Class actorType){

        return actorProducers.get(actorType);
    }
}
