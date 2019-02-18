package com.pf.sys.util;

import com.pf.sys.entity.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Author: Ru He
 * Date: Created in 2019/1/22.
 * Description: 处理session工具类
 */
public class SessionUtil {

    /**
     * 获取管理员id
     */
    public static String getUserId(){
        Manager manager = getUser();
        if(manager != null){
            return manager.getId();
        }
        return null;
    }

    /**
     * 获取管理员实体
     */
    public static Manager getUser(){
        Session session = SecurityUtils.getSubject().getSession();
        Manager manager = (Manager)session.getAttribute("userSession");
        return manager;
    }

}
