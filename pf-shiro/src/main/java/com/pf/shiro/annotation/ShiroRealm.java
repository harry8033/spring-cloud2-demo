package com.pf.shiro.annotation;

import java.lang.annotation.*;

/**
 * Author: Ru He
 * Date: Created in 2019/1/14.
 * Description: 注解，用来扫描用户自定义的Realm
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface ShiroRealm {
    String value() default  "";
}
