package cn.luoxx.shiro.realm;

import cn.luoxx.shiro.config.ShiroCasConfig;
import cn.luoxx.shiro.dao.UserDao;
import cn.luoxx.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MyShiroRealm extends CasRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void initProperty() {
        // setDefaultRoles("ROLE_USER");
        setCasServerUrlPrefix(ShiroCasConfig.casServerUrlPrefix);
        // 客户端回调地址
        setCasService(ShiroCasConfig.shiroServerUrlPrefix + ShiroCasConfig.casFilterUrlPattern);
    }

    /**
     * 1、CAS认证 ,验证用户身份
     * 2、将用户基本信息设置到会话中(不用了，随时可以获取的)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
        String account = (String) authc.getPrincipals().getPrimaryPrincipal();
        User user = userDao.findByName(account);
        if (user != null) {
            //将用户信息存入session中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
        }
        return authc;
    }
}
