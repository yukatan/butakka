package test.supervision.router_actor_with_decider;

import akka.actor.SupervisorStrategy;
import com.butakka.annotations.StrategyHandler;
import com.butakka.annotations.SupervisionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import test.mock.MockBean;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
@SupervisionStrategy(actorId = "actor-with-decider")
public class DeciderForTheRouter {

    @Autowired
    private MockBean mockBean;

    @StrategyHandler
    public SupervisorStrategy.Directive handleException(RuntimeException exception){

        mockBean.testMethod(exception.getMessage());
        return SupervisorStrategy.resume();
    }
}
