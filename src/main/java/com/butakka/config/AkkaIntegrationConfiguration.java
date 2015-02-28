package com.butakka.config;

import akka.actor.ActorSystem;
import akka.actor.ExtendedActorSystem;
import com.butakka.annotations.EnableAkkaIntegration;
import com.butakka.infrastructure.AkkaContext;
import com.butakka.infrastructure.extension.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by jefe on 21/02/15.
 */
@ConditionalOnClass(EnableAkkaIntegration.class)
public class AkkaIntegrationConfiguration {

    @Autowired
    private ApplicationContext context;


    @Bean
    private AkkaContext actorContext(){

        return new AkkaContext();
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
                .create("default-basic-system", akkaConfiguration());
        SpringExtension.SpringExtProvider.createExtension((ExtendedActorSystem)system).initialize(context);
        return system;
    }



}
