package com.pf.sys.shiro.util;

import com.pf.sys.entity.Manager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;

/**
 * Author: Ru He
 * Date: Created in 2019/2/16.
 * Description:
 */
public class ShiroUtils {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubjct().logout();
    }

    public static Manager getUser() {
        Manager manager = null;
        Object obj = getSubjct().getPrincipal();
        if (StringUtils.isEmpty(obj)) {
            manager = new Manager();
            try {
                BeanUtils.copyProperties(manager, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return manager;
    }

}
