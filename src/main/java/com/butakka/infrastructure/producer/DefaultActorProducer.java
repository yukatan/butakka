package com.butakka.infrastructure.producer;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * Created by jefe on 21/02/15.
 */
public class DefaultActorProducer<T extends Actor> implements IndirectActorProducer{

    private ApplicationContext context;
    private Class<T> actorType;

    public DefaultActorProducer(ApplicationContext context, Class<T> actorType) {
         this.context = context;
         this.actorType = actorType;
    }

    @Override
    public Actor produce() {
        return  context.getBean(actorType);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return  actorType;
    }
}
