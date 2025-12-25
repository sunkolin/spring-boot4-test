package com.starfish.test.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Login
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2024-05-10
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface Login {


}
