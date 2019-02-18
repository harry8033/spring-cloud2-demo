package com.pf.sys.controller;

import com.pf.core.entity.Result;
import com.pf.spring.base.BaseController;
import com.pf.sys.entity.Manager;
import com.pf.sys.shiro.token.ManagerToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2019/1/14.
 * Description:
 */
@Controller
public class LoginController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> params){
        //Map<String, String> params = getRequestParamsStr();
        Result result = new Result();
        try{
            ManagerToken token = new ManagerToken(params.get("account"), params.get("cryppwd"));
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            Session session = subject.getSession(true);
            Manager u = (Manager)session.getAttribute("userSession");
            //userLoginService.saveLoginLog(u, request);
            //request.setAttribute("user", u);
            //request.removeAttribute("error");
        }catch(LockedAccountException e){
            LOG.warn("user [" + params.get("account") + "] is locked");
            //request.setAttribute("error", "该账号被禁止登陆");
            result.setCode(3001);
            result.setMsg("user account is disabled.");
        }catch(UnknownAccountException e){
            LOG.warn("user [" + params.get("account") + "] not found");
            //request.setAttribute("error", "输入的账号不存在");
            result.setCode(3002);
            result.setMsg("user account is not found.");
        }catch(IncorrectCredentialsException e){
            LOG.warn("user [" + params.get("account") + "] pwd error");
            //request.setAttribute("error", "用户名或密码错误");
            result.setCode(3003);
            result.setMsg("user account or password error.");
        }catch(Exception e){
            LOG.error("用户登录失败", e);
            //request.setAttribute("error", "用户名或密码错误");
            result.setCode(999);
            result.setMsg("user account or password error.");
        }

        //return "index";
        return result;
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return "login";
    }

}
