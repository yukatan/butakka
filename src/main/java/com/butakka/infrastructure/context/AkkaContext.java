package com.butakka.infrastructure.context;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorContext;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
public interface AkkaContext {
    ActorRef getActorRef(Class actorType);

    ActorRef getActorRef(Class actorType, String name);

    ActorRef getActorRef(Class actorType, UntypedActorContext context);

    ActorRef getActorRef(Class actorType, UntypedActorContext context, String name);

    ActorSystem getSystem();

    Props getActorProps(Class actorType);
}
