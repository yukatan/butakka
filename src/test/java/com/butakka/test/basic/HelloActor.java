package com.butakka.test.basic;

import akka.actor.UntypedActor;
import com.butakka.annotations.AkkaActor;
import com.butakka.infrastructure.context.AkkaContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jefe on 21/02/15.
 */
@AkkaActor
public class HelloActor extends UntypedActor {

    @Autowired
    private AkkaContext context;

    @Override
    public void onReceive(Object message) throws Exception {

        String msg = (String) message;
        getSender().tell("OK:" + message,getSelf());
    }
}
