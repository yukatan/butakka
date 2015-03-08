package com.butakka.infrastructure.routing.strategies;

import akka.actor.OneForOneStrategy;
import akka.routing.SmallestMailboxPool;
import com.butakka.annotations.RouterActor;
import com.butakka.annotations.RouterStrategy;
import com.butakka.infrastructure.routing.AbstractRouterStrategyConfigurer;
import com.butakka.infrastructure.routing.strategies.decider.Decider;
import com.butakka.infrastructure.routing.strategies.decider.InternalDecider;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@RouterStrategy(SmallestMailboxPool.class)
public class SmallestMailboxPoolRouterStrategy extends AbstractRouterStrategyConfigurer {

    //todo:support more strategies
    @Override
    public void configure(RouterActor annotation) {

        SmallestMailboxPool poll = new SmallestMailboxPool(annotation.instances());
        if(hasSupervision(annotation.actorId())){
            Decider decider = supervisorStrategyContext.lookupDeciderByActorId(annotation.actorId());
            InternalDecider internalDecider = new InternalDecider(decider);
            poll = poll.withSupervisorStrategy(new OneForOneStrategy(20, Duration.create(1, TimeUnit.MINUTES),internalDecider,true));
            actorDefinerContext.get(annotation.actorId()).setDecider(decider);
        }
        actorDefinerContext.get(annotation.actorId()).setConfig(poll);

    }

}
