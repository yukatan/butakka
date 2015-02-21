package com.butakka.annotations;

import com.butakka.config.AkkaIntegrationConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jefe on 21/02/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = AkkaIntegrationConfiguration.class)
public @interface EnableAkkaIntegration {
}
