package com.butakka.infrastructure.context;

import com.butakka.infrastructure.definer.ActorDefiner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@Component
public class ActorDefinerContext {

   private HashMap<String,ActorDefiner> definers = new HashMap<String, ActorDefiner>();

    public HashMap<String, ActorDefiner> getDefiners() {
        return definers;
    }

    public void setDefiners(HashMap<String, ActorDefiner> definers) {
        this.definers = definers;
    }

    public void addDefiner(ActorDefiner definer){

        definers.put(definer.getActorId(),definer);
    }

    public ActorDefiner get(String  actorId){

        return definers.get(actorId);
    }
}
