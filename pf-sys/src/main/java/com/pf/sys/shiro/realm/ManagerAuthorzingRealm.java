package com.pf.sys.shiro.realm;

import com.pf.shiro.annotation.ShiroRealm;
import com.pf.spring.util.SpringUtil;
import com.pf.sys.entity.Manager;
import com.pf.sys.service.ManagerService;
import com.pf.sys.shiro.token.ManagerToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Author: Ru He
 * Date: Created in 2019/1/12.
 * Description: 管理员 shiro realm 类
 */
@ShiroRealm
public class ManagerAuthorzingRealm extends AuthorizingRealm {

    public ManagerAuthorzingRealm(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        //return hashedCredentialsMatcher;
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof ManagerToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ManagerService service = SpringUtil.getBean(ManagerService.class);
        Manager user = service.findByAccount((String) authenticationToken.getPrincipal());
        if (user != null){

            if (user.getState() == 0) {
                throw new LockedAccountException(); // 帐号锁定
            }

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
