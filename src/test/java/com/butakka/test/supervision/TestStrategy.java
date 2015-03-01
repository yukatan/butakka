package com.butakka.test.supervision;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.ChildRestartStats;
import akka.actor.SupervisorStrategy;
import com.butakka.annotations.SupervisionStrategy;
import scala.PartialFunction;
import scala.collection.Iterable;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
@SupervisionStrategy
public class TestStrategy extends SupervisorStrategy {

    @Override
    public PartialFunction<Throwable, Directive> decider() {
        return null;
    }

    @Override
    public void handleChildTerminated(ActorContext actorContext, ActorRef actorRef, scala.collection.Iterable<ActorRef> actorRefIterable) {

    }

    @Override
    public void processFailure(ActorContext actorContext, boolean b, ActorRef actorRef, Throwable throwable, ChildRestartStats childRestartStats, Iterable<ChildRestartStats> childRestartStatsIterable) {

    }
}
