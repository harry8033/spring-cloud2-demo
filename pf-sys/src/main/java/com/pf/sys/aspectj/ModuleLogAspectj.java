package com.pf.sys.aspectj;

import com.alibaba.fastjson.JSON;
import com.pf.sys.aspectj.annotation.ModuleLog;
import com.pf.sys.aspectj.enums.OptStatus;
import com.pf.sys.entity.Manager;
import com.pf.sys.entity.OptLog;
import com.pf.sys.service.OptLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Author: Ru He
 * Date: Created in 2019/2/16.
 * Description: 模块日志切面类
 */
@Aspect
@Component
public class ModuleLogAspectj {

    private final static Logger log = LoggerFactory.getLogger(ModuleLogAspectj.class);

    @Autowired
    private OptLogService optLogService;

    @Pointcut("@annotation(com.pf.sys.aspectj.annotation.ModuleLog)")
    public void pointcut() {
    }

    /**
     * 前置通知
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "pointcut()")
    public void doAfter(JoinPoint joinPoint){
        log.info("后置通知");
        handleModuleLog(joinPoint, null);
    }

    /**
     * 后置通知
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doException(JoinPoint joinPoint, Exception e){
        log.info("异常处理");
        handleModuleLog(joinPoint, e);
    }

    @Async
    public void handleModuleLog(JoinPoint joinPoint, Exception e){
        try{
            System.out.println(joinPoint.getSignature().getName());
            // 获得注解
            ModuleLog moduleLog = getAnnotationLog(joinPoint);
            // 获取用户
            //Manager manager = ShiroUtils.getUser();
            Manager manager = new Manager();
            manager.setId("e628528451344cf1845e0dd34226cd38");
            if (moduleLog == null || manager == null) {
                return;
            }

            OptLog optLog = new OptLog();
            optLog.setOper(manager.getId());
            optLog.setModule(moduleLog.module());
            optLog.setOpt(moduleLog.type().name());
            //设置方法参数
            optLog.setParams(getMethodParams(joinPoint));
            if(e != null){
                optLog.setState(OptStatus.EXCEPTION.name());
                optLog.setErr(e.getMessage());
            }else{
                optLog.setState(OptStatus.SUCCESS.name());
            }

            optLogService.addEntity(optLog);
        }catch (Exception ex){
            log.error("处理模块日志异常.", ex);
        }
    }

    /**
     * 获取ModuleLog注解
     */
    private ModuleLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(ModuleLog.class);
        }
        return null;
    }

    /**
     * 获取方法参数
     */
    private String getMethodParams(JoinPoint joinPoint){
        Object[] args =joinPoint.getArgs();
        if(args != null && args[0] != null){
            return JSON.toJSONString(args[0]);
        }
        return null;
    }

}
