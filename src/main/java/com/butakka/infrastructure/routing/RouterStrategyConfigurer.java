package com.butakka.infrastructure.routing;

import com.butakka.annotations.RouterActor;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
public interface RouterStrategyConfigurer {

    void configure(RouterActor annotation);
}
