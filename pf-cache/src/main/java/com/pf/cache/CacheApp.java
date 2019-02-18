package com.pf.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Ru He
 * Date: Created in 2019/1/21.
 * Description:
 */
@SpringBootApplication
@Controller
public class CacheApp {


    //@Autowired
    //private EhCache ehCache;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(CacheApp.class);
        //InetAddress inetAddress = InetAddress.getLocalHost();
        //System.out.println(inetAddress.getHostAddress());
    }

    @ResponseBody
    @RequestMapping("/test")
    public Object test(){
        //ehCache.setCache("test", "hello");
        return EhCache.getCache("test");
    }

    @ResponseBody
    @RequestMapping("/index")
    public Object index(){
        //ehCache.setCache("test", "hello");
        return EhCache.getCache("index");
    }

    @ResponseBody
    @RequestMapping("/set")
    public Object setTest(){
        EhCache.setCache("index", "hello cache app");
        return EhCache.getCache("index");
    }
}
