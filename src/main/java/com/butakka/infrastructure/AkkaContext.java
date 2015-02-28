package com.butakka.infrastructure;

import akka.actor.*;
import com.butakka.annotations.AkkaActor;
import com.butakka.error.ActorTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jefe on 21/02/15.
 */
public class AkkaContext implements Extension {

    @Autowired
    private ActorSystem system;

    @Autowired
    private ApplicationContext context;

    private Map<Class,Props> actorProducers = new HashMap<Class, Props>();

    @PostConstruct
    public void processActors(){

        String[] actorNames = context.getBeanNamesForAnnotation(AkkaActor.class);
        for(String name : actorNames){

            Class actorType = context.getType(name);
            Props producer = Props.create(DefaultActorProducer.class,context,actorType);
            actorProducers.put(actorType, producer);
        }
    }

    public ActorRef getActorRef(Class actorType){

        Props props = actorProducers.get(actorType);
        return system.actorOf(props);

    }

    public ActorRef getActorRef(Class actorType,String name){

        Props props = actorProducers.get(actorType);
        if(props == null)
            throw new ActorTypeNotFoundException(actorType.getCanonicalName());
        return system.actorOf(props,name);
    }
    public ActorRef getActorRef(Class actorType,UntypedActorContext context){

        Props props = actorProducers.get(actorType);
        return context.actorOf(props);
    }

    public ActorRef getActorRef(Class actorType,UntypedActorContext context,String name){

        Props props = actorProducers.get(actorType);
        return system.actorOf(props,name);
    }

    public ActorSystem getSystem() {
        return system;
    }

    public Props getActorProps(Class actorType){

        return actorProducers.get(actorType);
    }
}
