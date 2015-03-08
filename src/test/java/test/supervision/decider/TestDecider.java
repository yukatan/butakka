package test.supervision.decider;

import akka.actor.SupervisorStrategy;
import com.butakka.annotations.StrategyHandler;
import com.butakka.annotations.SupervisionStrategy;
import com.butakka.error.decider.DeciderHandlerNumberParameterException;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
@SupervisionStrategy(actorId = "supervision-actor-test")
public class TestDecider{


    @StrategyHandler
    public SupervisorStrategy.Directive testException(DeciderHandlerNumberParameterException exception){

       exception.printStackTrace();
       return SupervisorStrategy.restart();
    }
}
