package test.router.actors;

import akka.actor.UntypedActor;
import akka.routing.SmallestMailboxPool;
import com.butakka.annotations.AkkaActor;
import com.butakka.annotations.RouterActor;

/**
 * Created by jefe on 22/02/15.
 */
@RouterActor(actorId = "router-actor",value = SmallestMailboxPool.class)
public class RouterActorRoundRobinPool extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {

    }
}
