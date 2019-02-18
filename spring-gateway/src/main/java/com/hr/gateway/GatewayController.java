package com.pf.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Ru He
 * Date: Created in 2018/12/17.
 * Description:
 */
@Controller
public class GatewayController {

    @ResponseBody
    @RequestMapping("/gt/test")
    public String test(){
        return "success";
    }

}
