package com.butakka.infrastructure.definer;

import com.butakka.annotations.AkkaActor;
import com.butakka.annotations.RouterActor;
import com.butakka.infrastructure.definer.ActorDefiner;
import com.butakka.infrastructure.context.ActorDefinerContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@Component
public class ActorDefinerPostProcess implements BeanFactoryPostProcessor,Ordered{


    private ActorDefinerContext actorDefinerContainer;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        actorDefinerContainer = configurableListableBeanFactory.getBean(ActorDefinerContext.class);
        String[] actorNames = configurableListableBeanFactory.getBeanNamesForAnnotation(AkkaActor.class);
        String[] routerActorNames = configurableListableBeanFactory.getBeanNamesForAnnotation(RouterActor.class);
        for(String name: actorNames)
           processActor(configurableListableBeanFactory.getType(name));
        for(String name: routerActorNames)
            processRouterActor(configurableListableBeanFactory.getType(name));
    }

    private void processActor(Class beanClass){

        AkkaActor annotation = AnnotationUtils.findAnnotation(beanClass,AkkaActor.class);
        if(annotation != null){
            ActorDefiner definer = new ActorDefiner(annotation.value(),beanClass);
            actorDefinerContainer.addDefiner(definer);
            return;
        }
    }

    private void processRouterActor(Class beanClass){

        RouterActor routerAnnotation = AnnotationUtils.findAnnotation(beanClass,RouterActor.class);
        if(routerAnnotation != null){
            ActorDefiner definer = new ActorDefiner(routerAnnotation.actorId(),beanClass);
            actorDefinerContainer.addDefiner(definer);
            return;
        }

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
