package com.pf.sys.aspectj.annotation;

import com.pf.sys.aspectj.enums.OptType;

import java.lang.annotation.*;

/**
 * Author: Ru He
 * Date: Created in 2019/2/16.
 * Description: 记录模块操作日志注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleLog {

    /**
     * 模块名
     */
    String module() default "";

    /**
     * 操作类型
     */
    OptType type() default OptType.OTHER;

}
