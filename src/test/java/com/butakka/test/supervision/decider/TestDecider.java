package com.butakka.test.supervision.decider;

import akka.actor.SupervisorStrategy;
import com.butakka.annotations.StrategyDecider;
import com.butakka.annotations.StrategyHandler;
import com.butakka.error.DeciderHandlerNumberParameterException;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
@StrategyDecider
public class TestDecider{


    @StrategyHandler
    public SupervisorStrategy.Directive testException(DeciderHandlerNumberParameterException exception){

       exception.printStackTrace();
       return SupervisorStrategy.restart();
    }
}
