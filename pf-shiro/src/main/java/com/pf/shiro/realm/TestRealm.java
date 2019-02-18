package com.pf.shiro.realm;

import com.pf.shiro.token.ManagerToken;
import com.pf.spring.util.SpringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Author: Ru He
 * Date: Created in 2019/1/15.
 * Description:
 */
public class TestRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "com.pf.sys.shiro.realm.ManagerRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ManagerToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object o = SpringUtil.getBean("com.pf.sys.service.ManagerService");
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
