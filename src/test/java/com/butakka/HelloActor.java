package com.butakka;

import akka.actor.UntypedActor;
import com.butakka.annotations.AkkaActor;

/**
 * Created by jefe on 21/02/15.
 */
@AkkaActor(name = "HelloActor")
public class HelloActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {

        String msg = (String) message;
        System.out.println(msg);
    }
}
