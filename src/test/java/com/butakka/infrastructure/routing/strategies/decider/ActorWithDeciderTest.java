package com.butakka.infrastructure.routing.strategies.decider;

import akka.actor.ActorRef;
import akka.testkit.JavaTestKit;
import com.butakka.config.AkkaIntegrationConfiguration;
import com.butakka.infrastructure.context.AkkaContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.mock.MockBean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AkkaIntegrationConfiguration.class, ActorWithDeciderTest.Config.class})
@ActiveProfiles("test")
public class ActorWithDeciderTest {

    @Configuration
    @Profile("test")
    @ComponentScan("test.supervision.router_actor_with_decider")
    static class Config{

        @Bean
        public MockBean mockBean(){

            return mock(MockBean.class);
        }

    }

    @Autowired
    private MockBean mockBean;

    @Autowired
    private AkkaContext context;


    @Test
    public void testThatTheMethodOnMockIsCalledOnException() throws Exception {

        new JavaTestKit(context.getSystem()) {{
            final ActorRef subject = context.getActorRef("actor-with-decider", "router");
            subject.tell(new RuntimeException("TEST"), getRef());
            expectNoMsg();
            verify(mockBean,times(1)).testMethod("TEST");
        }};

    }
}
