package com.butakka.infrastructure.routing;

import com.butakka.annotations.RouterActor;
import com.butakka.infrastructure.context.ActorDefinerContext;
import com.butakka.infrastructure.context.SupervisorStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
public abstract class AbstractRouterStrategyConfigurer implements RouterStrategyConfigurer{

    @Autowired
    protected SupervisorStrategyContext supervisorStrategyContext;

    @Autowired
    protected ActorDefinerContext actorDefinerContext;

    abstract public void configure(RouterActor annotation);


    protected boolean hasSupervision(String actorId){

        return supervisorStrategyContext.lookupDeciderByActorId(actorId) != null;
    }
}
