package com.butakka.infrastructure.routing.strategies.regular;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.ChildRestartStats;
import akka.actor.SupervisorStrategy;
import scala.PartialFunction;
import scala.collection.Iterable;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
public class DefaultSupervisionStrategy extends SupervisorStrategy{

    @Override
    public PartialFunction<Throwable, Directive> decider() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handleChildTerminated(ActorContext actorContext, ActorRef actorRef, scala.collection.Iterable<ActorRef> actorRefIterable) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void processFailure(ActorContext actorContext, boolean b, ActorRef actorRef, Throwable throwable, ChildRestartStats childRestartStats, Iterable<ChildRestartStats> childRestartStatsIterable) {
        throw new UnsupportedOperationException();
    }
}
