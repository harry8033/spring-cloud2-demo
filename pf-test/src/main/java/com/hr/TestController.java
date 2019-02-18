package com.hr;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Ru He
 * Date: Created in 2019/1/16.
 * Description:
 */
@RestController
@CacheConfig(cacheNames = "cache_user")
public class TestController {

    @GetMapping("/index")
    @Cacheable(sync = true, key = "'index'")
    public String index(){
        //return "index2";
        return get();
    }

    public String get(){
        System.out.println("============");
        return "hello";
    }

    @GetMapping("/test")
    @Cacheable(sync = true, key = "'test'")
    public String test(){
        //return "index2";
        return "hello test1";
    }
}
