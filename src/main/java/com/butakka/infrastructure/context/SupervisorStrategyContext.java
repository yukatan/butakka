package com.butakka.infrastructure.context;

import akka.actor.SupervisorStrategy;
import com.butakka.annotations.StrategyHandler;
import com.butakka.annotations.SupervisionStrategy;
import com.butakka.error.decider.DeciderHandlerNumberParameterException;
import com.butakka.error.decider.DeciderHandlerReturnTypeException;
import com.butakka.error.decider.DeciderParameterTypeException;
import com.butakka.infrastructure.routing.strategies.decider.Decider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@Component
public class SupervisorStrategyContext {

    @Autowired
    private ApplicationContext context;

    Map<String,Decider> deciders = new HashMap<String, Decider>();

    @PostConstruct
    public void initSupervisorStrategies(){

        Map<String, Object> beans = context.getBeansWithAnnotation(SupervisionStrategy.class);
        for(Object bean:beans.values()){
             process(bean);
        }
    }

    private void process(Object bean) {

        SupervisionStrategy deciderAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), SupervisionStrategy.class);
        Decider decider = new Decider();
        decider.setDecider(bean);
        for (Method method : bean.getClass().getMethods()){
            StrategyHandler annotation = AnnotationUtils.findAnnotation(method, StrategyHandler.class);
            if(annotation == null)
                continue;
            if(!SupervisorStrategy.Directive.class.isAssignableFrom(method.getReturnType())){
                throw  new DeciderHandlerReturnTypeException();
            }
            if(method.getParameterTypes().length > 1){
                throw  new DeciderHandlerNumberParameterException();
            }
            Class typeParameter = method.getParameterTypes()[0];
            if(!Throwable.class.isAssignableFrom(typeParameter)){
                throw  new DeciderParameterTypeException();
            }
            decider.getHandlers().put(typeParameter.getCanonicalName(),method);
        }
        deciders.put(deciderAnnotation.actorId(),decider);
    }

    public Decider lookupDeciderByActorId(String actorId){

        return deciders.get(actorId);
    }
}
