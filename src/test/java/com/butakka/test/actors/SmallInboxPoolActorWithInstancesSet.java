package com.butakka.test.actors;

import akka.actor.UntypedActor;
import akka.routing.SmallestMailboxPool;
import com.butakka.annotations.AkkaActor;
import com.butakka.annotations.WithRouter;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@AkkaActor
@WithRouter(value = SmallestMailboxPool.class,instances = 5)
public class SmallInboxPoolActorWithInstancesSet extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {

    }
}
