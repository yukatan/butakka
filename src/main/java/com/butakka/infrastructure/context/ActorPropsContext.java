package com.butakka.infrastructure.context;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.butakka.annotations.RouterActor;
import com.butakka.infrastructure.definer.ActorDefiner;
import com.butakka.infrastructure.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@Component
public class ActorPropsContext {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ActorDefinerContext definerContainer;

    @Autowired
    private ActorSystem system;

    @Autowired
    private RouterStrategyContext routerStrategyContext;

    private HashMap<String,Props> actorProps = new HashMap<String,Props>();

    @PostConstruct
    public void contextStartup(){

        String[] beanNames = context.getBeanNamesForAnnotation(RouterActor.class);
        for(String name : beanNames){
            RouterActor actor = AnnotationUtils.findAnnotation(context.getType(name), RouterActor.class);
            routerStrategyContext.get(actor.value()).configure(actor);
        }
        for(ActorDefiner definer : definerContainer.getDefiners().values()){

            Props props = SpringExtension.SpringExtProvider.get(system).props(definer.getType());
            if(definer.getConfig() != null)
                props = props.withRouter(definer.getConfig());
            actorProps.put(definer.getActorId(),props);
        }
    }

    public Props getProps(String actorId){

        return actorProps.get(actorId);
    }
}
