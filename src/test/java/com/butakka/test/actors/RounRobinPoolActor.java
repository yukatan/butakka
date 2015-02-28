package com.butakka.test.actors;

import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.butakka.annotations.AkkaActor;
import com.butakka.annotations.WithRouter;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@AkkaActor
@WithRouter(RoundRobinPool.class)
public class RounRobinPoolActor extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {

    }
}
