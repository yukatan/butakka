package com.butakka.infrastructure.configurer;

import akka.actor.Props;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
public interface ActorConfigurer {

    Props configure(Class actorType,Props props);
    boolean isConfigurableBy(Class actorType);
}
