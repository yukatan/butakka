package test.supervision.router_actor_with_decider;

import akka.actor.UntypedActor;
import akka.routing.SmallestMailboxPool;
import com.butakka.annotations.RouterActor;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@RouterActor(actorId = "actor-with-decider",value = SmallestMailboxPool.class)
public class ActorWithDecider extends UntypedActor{

    @Override
    public void onReceive(Object o) throws Exception {

        if(o instanceof RuntimeException){

            RuntimeException exception = (RuntimeException) o;
            throw exception;
        }
    }
}
