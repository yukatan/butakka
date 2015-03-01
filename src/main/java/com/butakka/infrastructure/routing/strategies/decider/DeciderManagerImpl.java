package com.butakka.infrastructure.routing.strategies.decider;

import akka.actor.SupervisorStrategy;
import com.butakka.annotations.StrategyDecider;
import com.butakka.annotations.StrategyHandler;
import com.butakka.error.DeciderHandlerNumberParameterException;
import com.butakka.error.DeciderHandlerReturnTypeException;
import com.butakka.error.DeciderParameterTypeException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
@Component
public class DeciderManagerImpl implements BeanPostProcessor, DeciderManager {

    Map<String,Decider> deciders = new HashMap<String, Decider>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {

        StrategyDecider deciderAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), StrategyDecider.class);
        if(deciderAnnotation == null){
            return bean;
        }
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
        deciders.put(bean.getClass().getCanonicalName(),decider);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public Decider lookupDeciderByType(Class type){

        String name = type.getCanonicalName();
        return deciders.get(name);
    }
}
