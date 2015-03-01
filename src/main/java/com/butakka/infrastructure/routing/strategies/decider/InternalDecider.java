package com.butakka.infrastructure.routing.strategies.decider;

import akka.actor.SupervisorStrategy;
import akka.japi.Function;

import java.lang.reflect.Method;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
public class InternalDecider implements Function<Throwable,SupervisorStrategy.Directive> {


    private Decider decider;

    public InternalDecider(Decider decider) {
        this.decider = decider;
    }

    @Override
    public SupervisorStrategy.Directive apply(Throwable throwable) throws Exception {

        String type = throwable.getClass().getCanonicalName();
        Method method = decider.getHandlers().get(type);
        if(method == null){
            return SupervisorStrategy.escalate();
        }

        return (SupervisorStrategy.Directive) method.invoke(decider.getDecider(),throwable);
    }

}
