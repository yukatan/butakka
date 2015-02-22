package com.butakka.error;

/**
 * Created by jefe on 22/02/15.
 */
public class ActorTypeNotFoundException extends RuntimeException {

    public ActorTypeNotFoundException(String message) {
        super(message);
    }
}
