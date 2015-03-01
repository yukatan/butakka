package com.butakka.infrastructure.routing.strategies.decider;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
public interface DeciderManager {

    Decider lookupDeciderByType(Class type);
}
