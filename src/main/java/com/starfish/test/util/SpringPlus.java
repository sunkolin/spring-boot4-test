package com.starfish.test.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringPlus
 *
 * @author sunkolin
 * @version 1.0.0
 * @since 2024-04-08
 */
@Component
public class SpringPlus implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringPlus.applicationContext = applicationContext;
    }

}
