package com.pf.spring.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Ru He
 * Date: Created in 2019/1/16.
 * Description:
 */
@Controller
public class TestC implements ErrorController {

    private final static Logger log = LoggerFactory.getLogger(TestC.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        log.warn("HTTP {} Forbidden. url: {}", statusCode, request.getAttribute("javax.servlet.error.request_uri"));
        if(statusCode == 401){
            return "401";
        }else if(statusCode == 404){
            return "404";
        }else if(statusCode == 403){
            return "403";
        }else{
            return "500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
