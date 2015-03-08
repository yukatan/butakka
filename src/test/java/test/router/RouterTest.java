package test.router;

import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import com.butakka.config.AkkaIntegrationConfiguration;
import com.butakka.infrastructure.context.DefaultAkkaContext;
import org.springframework.boot.test.SpringApplicationConfiguration;
import test.router.actors.RouterActorRoundRobinPool;
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
@SpringApplicationConfiguration(classes = {AkkaIntegrationConfiguration.class, RouterTest.configuration.class})
public class RouterTest {

    @Configuration
    @ComponentScan(basePackages = "test.router")
    static class configuration{

    }

    @Autowired
    private DefaultAkkaContext context;

    @Test
    public void TestThatTheRouterWorks() throws Exception {

        new JavaTestKit(context.getSystem()) {{
            final ActorRef subject = context.getActorRef("router-actor", "router");
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
