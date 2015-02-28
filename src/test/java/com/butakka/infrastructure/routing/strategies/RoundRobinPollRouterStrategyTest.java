package com.butakka.infrastructure.routing.strategies;

import akka.routing.RoundRobinPool;
import akka.routing.SmallestMailboxPool;
import akka.testkit.TestActorRef;
import com.butakka.config.AkkaIntegrationConfiguration;
import com.butakka.infrastructure.context.AkkaContext;
import com.butakka.test.actors.RounRobinPoolActor;
import com.butakka.test.actors.SmallInboxPoolActor;
import com.butakka.test.actors.SmallInboxPoolActorWithInstancesSet;
import com.butakka.test.config.ActorTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AkkaIntegrationConfiguration.class, ActorTestConfig.class})
@DirtiesContext
public class RoundRobinPollRouterStrategyTest {

    @Autowired
    private AkkaContext context;


    @Test
    public void TheRouterConfigShouldBeRoundRobinPool() throws Exception {

        TestActorRef ref = TestActorRef.create(context.getSystem(),context.getActorProps(RounRobinPoolActor.class));
        assertEquals(ref.underlyingActor().context().props().routerConfig().getClass(),RoundRobinPool.class);

    }

    @Test
    public void TheRouterConfigInstancesShouldBeDefault() throws Exception {

        TestActorRef ref = TestActorRef.create(context.getSystem(),context.getActorProps(RounRobinPoolActor.class));
        RoundRobinPool pool = (RoundRobinPool) ref.underlyingActor().context().props().routerConfig();
        assertEquals(1,pool.nrOfInstances());

    }


}
