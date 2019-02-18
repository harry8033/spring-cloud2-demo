package com.pf.spring.exception;

import com.pf.core.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: Ru He
 * Date: Created in 2019/1/7.
 * Description: SpringBoot 全局异常处理，将异常处理成JSON格式的响应结果
 */
@RestControllerAdvice
public class ApplicationAdvice {

    private final static Logger log = LoggerFactory.getLogger(ApplicationAdvice.class);

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result handleNotSupport(Exception e, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers","Access-Control");
        response.setHeader("Allow","*");
        //log.warn("not support request, message: {}", e.getMessage());
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return Result.asNotSupported(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result handException(Exception e){
        log.error("found system error.", e);
        return Result.asSysErr();
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Result handNotFoundException(Exception e){
        log.error("file not found.", e);
        return Result.asNotFound();
    }

}
