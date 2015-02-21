package com.butakka.infrastructure;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import com.butakka.annotations.AkkaActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jefe on 21/02/15.
 */
public class ActorContext implements Extension {

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


}
