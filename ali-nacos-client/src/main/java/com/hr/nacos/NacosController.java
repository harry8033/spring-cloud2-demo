package com.pf.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Ru He
 * Date: Created in 2018/12/17.
 * Description:
 */
@RestController
@RefreshScope
public class NacosController {

    @Value("${flag:false}")
    private boolean flag;

    @RequestMapping("/test/hello")
    public boolean test(){
        return flag;
    }

}
