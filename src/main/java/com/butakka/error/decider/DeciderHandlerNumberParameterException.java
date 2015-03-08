package com.butakka.error.decider;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
public class DeciderHandlerNumberParameterException extends RuntimeException {

    public DeciderHandlerNumberParameterException() {
        super("The strategy handler method has more than one parameter");
    }
}
