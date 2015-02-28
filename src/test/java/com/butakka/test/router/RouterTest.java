package com.butakka.test.router;

import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import com.butakka.config.AkkaIntegrationConfiguration;
import com.butakka.infrastructure.AkkaContext;
import com.butakka.test.router.actors.RouterActorRoundRobinPool;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jefe on 22/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AkkaIntegrationConfiguration.class, RouterTest.configuration.class})
@DirtiesContext
public class RouterTest {

    @Configuration
    @ComponentScan(basePackages = "com.butakka.test.router")
    static class configuration{

    }

    @Autowired
    private AkkaContext context;

    @Test
    public void TestThatTheRouterWorks() throws Exception {

        new JavaTestKit(context.getSystem()) {{
            final ActorRef subject = context.getActorRef(RouterActorRoundRobinPool.class, "router");
            subject.tell("1", getRef());
            subject.tell("2", getRef());
            subject.tell("3", getRef());
            subject.tell("4", getRef());
            expectNoMsg();
        }};
    }

    @After
    public void tearDown() throws Exception {

        JavaTestKit.shutdownActorSystem(context.getSystem());

    }
}
