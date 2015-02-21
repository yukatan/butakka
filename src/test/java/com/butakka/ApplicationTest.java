package com.butakka;

import akka.actor.ActorRef;
import com.butakka.infrastructure.ActorContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jefe on 21/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private ActorContext context;

    @Test
    public void testName() throws Exception {

        ActorRef ref = context.getActorRef(HelloActor.class);
        ref.tell("Hello World",null);
    }
}
