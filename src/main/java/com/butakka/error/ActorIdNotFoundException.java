package com.butakka.error;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
public class ActorIdNotFoundException extends RuntimeException {

    public ActorIdNotFoundException(String actorId) {

        super("Actor id : " +actorId+ " not found in the context." );
    }
}
