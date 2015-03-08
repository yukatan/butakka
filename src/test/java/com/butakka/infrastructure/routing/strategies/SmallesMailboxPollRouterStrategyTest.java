package com.butakka.infrastructure.routing.strategies;

import akka.routing.SmallestMailboxPool;
import akka.testkit.TestActorRef;
import com.butakka.config.AkkaIntegrationConfiguration;
import com.butakka.infrastructure.context.AkkaContext;
import test.actors.SmallInboxPoolActor;
import test.config.ActorTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ActorTestConfig.class,AkkaIntegrationConfiguration.class})
@ActiveProfiles("test")
public class SmallesMailboxPollRouterStrategyTest {

    @Autowired
    private AkkaContext context;


    @Test
    public void TheRouterConfigShouldBeSmallestMailboxPool() throws Exception {

        TestActorRef ref = TestActorRef.create(context.getSystem(),context.getActorProps("small-inbox-pool-actor"));
        assertEquals(ref.underlyingActor().context().props().routerConfig().getClass(),SmallestMailboxPool.class);

    }

    @Test
    public void TheRouterConfigInstancesShouldBeDefault() throws Exception {

        TestActorRef ref = TestActorRef.create(context.getSystem(),context.getActorProps("small-inbox-pool-actor"));
        SmallestMailboxPool pool = (SmallestMailboxPool) ref.underlyingActor().context().props().routerConfig();
        assertEquals(1,pool.nrOfInstances());

    }



}
