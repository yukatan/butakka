package com.butakka.infrastructure.definer;

import akka.actor.SupervisorStrategy;
import akka.routing.RouterConfig;
import com.butakka.infrastructure.routing.strategies.decider.Decider;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
public class ActorDefiner {

    private String actorId;
    private Class type;
    private RouterConfig config;
    private SupervisorStrategy strategy;
    private Decider decider;

    public ActorDefiner(String actorId, Class type) {
        this.actorId = actorId;
        this.type = type;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public RouterConfig getConfig() {
        return config;
    }

    public void setConfig(RouterConfig config) {
        this.config = config;
    }

    public SupervisorStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SupervisorStrategy strategy) {
        this.strategy = strategy;
    }

    public Decider getDecider() {
        return decider;
    }

    public void setDecider(Decider decider) {
        this.decider = decider;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
