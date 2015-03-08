package com.butakka.infrastructure.context;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorContext;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
public interface AkkaContext {

    ActorRef getActorRef(String actorId);

    ActorRef getActorRef(String actorId, String name);

    ActorRef getActorRef(String actorId, UntypedActorContext context);

    ActorRef getActorRef(String actorId, UntypedActorContext context, String name);

    ActorSystem getSystem();

    Props getActorProps(String actorId);
}
