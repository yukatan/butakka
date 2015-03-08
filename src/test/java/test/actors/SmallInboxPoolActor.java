package test.actors;

import akka.actor.UntypedActor;
import akka.routing.SmallestMailboxPool;
import com.butakka.annotations.AkkaActor;
import com.butakka.annotations.RouterActor;
import org.springframework.stereotype.Component;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@RouterActor(actorId = "small-inbox-pool-actor",value = SmallestMailboxPool.class)
public class SmallInboxPoolActor extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {

    }
}
