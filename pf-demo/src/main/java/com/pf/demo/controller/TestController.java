package com.pf.demo.controller;

import com.pf.core.entity.Result;
import com.pf.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Ru He
 * Date: Created in 2019/1/10.
 * Description:
 */
@RestController
@CacheConfig(cacheNames = "cache_user")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Result test(){
        return new Result();
    }

    @Cacheable(sync = true)
    public String getUser(){
        return te();
    }

    private String te(){
        System.out.println("==================");
        return "hello";
    }

    @RequestMapping(value = "testAsync", method = RequestMethod.GET)
    public String testAsync(){
        testService.testAsync();
        return "success";
    }

}
