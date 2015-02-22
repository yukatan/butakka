package com.butakka.test.router.actors;

import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.butakka.annotations.AkkaActor;
import com.butakka.annotations.WithRouter;

/**
 * Created by jefe on 22/02/15.
 */
@AkkaActor
@WithRouter
public class RouterActorRoundRobinPool extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {

    }
}
