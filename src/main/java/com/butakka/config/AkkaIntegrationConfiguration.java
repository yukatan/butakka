package com.butakka.config;

import akka.actor.ActorSystem;
import com.butakka.annotations.EnableAkkaIntegration;
import com.butakka.infrastructure.ActorContext;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * Created by jefe on 21/02/15.
 */
@ConditionalOnClass(EnableAkkaIntegration.class)
public class AkkaIntegrationConfiguration {


    @Bean
    private ActorContext actorContext(){

        return new ActorContext();
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
                .create("default-basic-system", akkaConfiguration());
        return system;
    }



}
