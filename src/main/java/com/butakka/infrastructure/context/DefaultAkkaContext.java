package com.butakka.infrastructure.context;

import akka.actor.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Jesús Barquín on 21/02/15.
 */
@Component
public class DefaultAkkaContext implements AkkaContext ,BeanPostProcessor {

    @Autowired
    private ActorSystem system;

    @Autowired
    private ActorDefinerContext definerContainer;

    @Autowired
    private ActorPropsContext actorPropsContext;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public ActorRef getActorRef(String actorId){

        checkActor(actorId);
        Props props = actorPropsContext.getProps(actorId);
        return system.actorOf(props);

    }


    @Override
    public ActorRef getActorRef(String actorId, String name){

        checkActor(actorId);
        Props props = actorPropsContext.getProps(actorId);
        return system.actorOf(props,name);
    }

    @Override
    public ActorRef getActorRef(String actorId, UntypedActorContext context){

        checkActor(actorId);
        Props props = actorPropsContext.getProps(actorId);
        return context.actorOf(props);
    }


    @Override
    public ActorRef getActorRef(String actorId, UntypedActorContext context, String name){

        checkActor(actorId);
        Props props = actorPropsContext.getProps(actorId);
        return system.actorOf(props,name);
    }

    @Override
    public ActorSystem getSystem() {
        return system;
    }


    @Override
    public Props getActorProps(String actorId){

        checkActor(actorId);
        return actorPropsContext.getProps(actorId);

    }

    private void checkActor(String actorId){

//        if(!actorProps.containsKey(actorId));
//            instanciateProps(actorId);
//        if(!actorProps.containsKey(actorId))
//            throw new ActorIdNotFoundException(actorId);
    }
}
