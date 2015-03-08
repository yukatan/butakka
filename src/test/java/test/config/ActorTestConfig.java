package test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
@Configuration
@ComponentScan("test.actors")
@Profile("test")
public class ActorTestConfig {


}
