package com.pf.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author: Ru He
 * Date: Created in 2019/1/7.
 * Description: 用于普通javabean获取Spring的实例
 */
@Component
public class SpringUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
        //applicationContext.getAutowireCapableBeanFactory().
    }

    /**
     * 获取application context
     */
    public static ApplicationContext getApplicationConext(){
        return applicationContext;
    }

    /**
     * 通过类名获取实例
     * @param name 类名
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    /**
     * 通过类获取实例
     * @param clazz 类
     * @param <T> 泛型
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过类名和类获取实例
     * @param name 类名
     * @param clazz 类
     * @param <T> 泛型
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return applicationContext.getBean(name, clazz);
    }
}
