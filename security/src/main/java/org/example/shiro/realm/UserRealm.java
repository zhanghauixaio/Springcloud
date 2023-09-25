// package org.example.shiro.realm;
//
// import org.apache.shiro.SecurityUtils;
// import org.apache.shiro.authc.*;
// import org.apache.shiro.authz.AuthorizationInfo;
// import org.apache.shiro.authz.SimpleAuthorizationInfo;
// import org.apache.shiro.realm.AuthorizingRealm;
// import org.apache.shiro.subject.PrincipalCollection;
// import org.example.entity.SysUser;
//
// import java.util.HashSet;
//
// public class UserRealm extends AuthorizingRealm {
//     /**
//      * 权限认证/获取授权信息
//      * 该方法只有在需要权限认证时才会进入，
//      * 比如前面配置类中配置了
//      * filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色，
//      * 这时进入 /admin 时就会进入该方法来检查权限
//      */
//     @Override
//     protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//         String username = (String) SecurityUtils.getSubject().getPrincipal();
//
//         SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//         String role = "admin";
//         simpleAuthorizationInfo.addRole(role);
//         return simpleAuthorizationInfo;
//     }
//
//     /**
//      * 身份认证/获取身份验证信息
//      * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
//      * 该方法则是需要身份认证时（比如前面的 Subject.login() 方法）才会进入
//      */
//     @Override
//     protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//         UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//         SysUser user = new SysUser("admin", "123456");
//         return new SimpleAuthenticationInfo(usernamePasswordToken.getPrincipal(), user.getPassword(), user.getName());
//     }
// }
