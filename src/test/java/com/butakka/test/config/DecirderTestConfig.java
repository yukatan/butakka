package com.butakka.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jesus Barquín on 1/03/15.
 */
@Configuration
@Profile("test")
@ComponentScan("com.butakka.supervision.decider")
public class DecirderTestConfig {

}
