package com.pf.sys.shiro.realm;

import com.pf.shiro.annotation.ShiroRealm;
import com.pf.spring.util.SpringUtil;
import com.pf.sys.entity.Manager;
import com.pf.sys.service.ManagerService;
import com.pf.sys.shiro.token.ManagerToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;

/**
 * Author: Ru He
 * Date: Created in 2019/1/12.
 * Description: 管理员 shiro realm 类
 */
@ShiroRealm
public class ManagerRealm implements Realm {

    @Override
    public String getName() {
        return "com.pf.sys.shiro.realm.ManagerRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ManagerToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ManagerService service = SpringUtil.getBean(ManagerService.class);
        Manager user = service.findByAccount((String) authenticationToken.getPrincipal());
        if (user != null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes(user.getAccount() + "" + user.getSalt()),
                    getName());

            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession", user);
            //session.setAttribute("userSessionId", user.getId());

            return info;
        } else {
            throw new UnknownAccountException();// 没找到帐号
        }
    }
}
