package com.butakka.infrastructure.context;

import com.butakka.annotations.RouterStrategy;
import com.butakka.infrastructure.routing.RouterStrategyConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@Component
public class RouterStrategyContext {

    @Autowired
    private ApplicationContext context;


    private HashMap<Class,RouterStrategyConfigurer> configurers = new HashMap<Class, RouterStrategyConfigurer>();


    @PostConstruct
    public void initRouterConfig() {

        Map<String, Object> beans = context.getBeansWithAnnotation(RouterStrategy.class);
        for(Object bean : beans.values()){
            RouterStrategy annotation = AnnotationUtils.findAnnotation(bean.getClass(), RouterStrategy.class);
            configurers.put(annotation.value(), (RouterStrategyConfigurer) bean);
        }
    } 
    public RouterStrategyConfigurer get(Class type){

        return configurers.get(type);
    }



}
