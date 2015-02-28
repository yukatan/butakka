package com.butakka.config;

import akka.actor.ActorSystem;
import com.butakka.annotations.EnableAkkaIntegration;
import com.butakka.infrastructure.context.AkkaContext;
import com.butakka.infrastructure.context.DefaultAkkaContext;
import com.butakka.infrastructure.extension.SpringExtension;
import com.butakka.infrastructure.routing.RoutingActorConfigurer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by jefe on 21/02/15.
 */
@ConditionalOnClass(EnableAkkaIntegration.class)
@ComponentScan("com.butakka")
public class AkkaIntegrationConfiguration {

    @Autowired
    private ApplicationContext context;


    @Bean
    private AkkaContext actorContext(){

        return new DefaultAkkaContext();
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
                .create("default-basic-system", akkaConfiguration());
        SpringExtension.SpringExtProvider.get(system).initialize(context);
        return system;
    }


    @Bean
    public RoutingActorConfigurer routingActorConfigurer(){

        return new RoutingActorConfigurer();
    }
}
