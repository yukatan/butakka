package com.butakka.error.decider;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
public class DeciderHandlerReturnTypeException extends RuntimeException {

    public DeciderHandlerReturnTypeException() {
        super("The method return type must inherit from akka.actor.SupervisorStrategy.Directive");
    }
}
