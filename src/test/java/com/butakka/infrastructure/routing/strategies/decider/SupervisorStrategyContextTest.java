package com.butakka.infrastructure.routing.strategies.decider;

import akka.actor.SupervisorStrategy;
import com.butakka.config.AkkaIntegrationConfiguration;
import com.butakka.error.decider.DeciderHandlerNumberParameterException;
import com.butakka.infrastructure.context.SupervisorStrategyContext;
import org.springframework.test.context.ActiveProfiles;
import test.config.DecirderTestConfig;
import test.supervision.decider.TestDecider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AkkaIntegrationConfiguration.class, DecirderTestConfig.class})
@ActiveProfiles("test")
public class SupervisorStrategyContextTest {

    @Autowired
    private SupervisorStrategyContext context;


    @Test
    public void theDeciderTestDeciderShouldBeInTheDeciderManager() throws Exception {

        Decider decider = context.lookupDeciderByActorId("supervision-actor-test");
        assertNotNull(decider);
    }

    @Test
    public void theDeciderHandlerMethodMustBeInDecider() throws Exception {

        Decider decider = context.lookupDeciderByActorId("supervision-actor-test");
        Method method = decider.getHandlers().get(DeciderHandlerNumberParameterException.class.getCanonicalName());
        assertNotNull(method);
    }

    @Test
    public void theHandlerReturnTypeMustBeRestart() throws Exception {

        Decider decider = context.lookupDeciderByActorId("supervision-actor-test");
        Method method = decider.getHandlers().get(DeciderHandlerNumberParameterException.class.getCanonicalName());
        assertEquals(SupervisorStrategy.restart(),method.invoke(decider.getDecider(),new DeciderHandlerNumberParameterException()));
    }
}
