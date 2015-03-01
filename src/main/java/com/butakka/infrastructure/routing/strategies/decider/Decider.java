package com.butakka.infrastructure.routing.strategies.decider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
public class Decider {

    private Object decider;
    private Map<String,Method> handlers = new HashMap<String, Method>();



    public Object getDecider() {
        return decider;
    }

    public void setDecider(Object decider) {
        this.decider = decider;
    }

    public Map<String, Method> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, Method> handlers) {
        this.handlers = handlers;
    }
}
