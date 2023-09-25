// package org.example.shiro.config;
//
// import org.apache.shiro.cache.CacheManager;
// import org.apache.shiro.cache.MemoryConstrainedCacheManager;
// import org.apache.shiro.codec.Base64;
// import org.apache.shiro.mgt.SecurityManager;
// import org.apache.shiro.mgt.SessionsSecurityManager;
// import org.apache.shiro.realm.Realm;
// import org.apache.shiro.spring.LifecycleBeanPostProcessor;
// import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
// import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
// import org.apache.shiro.web.mgt.CookieRememberMeManager;
// import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
// import org.apache.shiro.web.servlet.SimpleCookie;
// // import org.example.shiro.filter.LoginFilter;
// import org.example.shiro.realm.UserRealm;
// import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.DependsOn;
//
// import javax.servlet.Filter;
// import java.util.LinkedHashMap;
// import java.util.Map;
//
// @Configuration
// public class ShiroConfig {
//     @Value("${shiro.user.loginUrl}")
//     private String loginUrl;
//
//     // @Bean
//     // public ShiroFilterFactoryBean shiroFilterFactoryBean() {
//     //     ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//     //     shiroFilterFactoryBean.setSecurityManager(securityManager());
//     //     shiroFilterFactoryBean.setLoginUrl("/toLogin");
//     //     Map<String, String> chainDefinitionMap = new LinkedHashMap<>();
//     //     chainDefinitionMap.put("/login", "anon");
//     //     // 自定义过滤器
//     //     // Map<String, Filter> filterMap = new LinkedHashMap<>();
//     //     // filterMap.put("login", loginFilter());
//     //     // shiroFilterFactoryBean.setFilters(filterMap);
//     //
//     //     chainDefinitionMap.put("/**", "authc");
//     //     shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitionMap);
//     //     return shiroFilterFactoryBean;
//     // }
//     @Bean
//     public SessionsSecurityManager securityManager(){
//         DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//         securityManager.setRealm(realm());
//         securityManager.setRememberMeManager(rememberMeManager());
//         securityManager.setCacheManager(cacheManager());
//         // securityManager.setSessionManager(sessionManager());
//         return securityManager;
//     }
//
//     @Bean
//     public Realm realm(){
//         return new UserRealm();
//     }
//
//     @Bean
//     public CacheManager cacheManager() {
//         return new MemoryConstrainedCacheManager();
//     }
//
//     @Bean
//     public SimpleCookie rememberMeCookie(){
//         //System.out.println("ShiroConfiguration.rememberMeCookie()");
//         //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//         SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//         //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//         simpleCookie.setMaxAge(259200);
//         return simpleCookie;
//     }
//
//     /**
//      * cookie管理对象;
//      * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
//      * @return
//      */
//     @Bean
//     public CookieRememberMeManager rememberMeManager(){
//         //System.out.println("ShiroConfiguration.rememberMeManager()");
//         CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//         cookieRememberMeManager.setCookie(rememberMeCookie());
//         //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//         cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
//         return cookieRememberMeManager;
//     }
//
//     // @Bean
//     // public SessionManager sessionManager(){
//     //
//     // }
//
//     /**
//      * Shiro生命周期处理器 * @return
//      */
//     @Bean
//     public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//         return new LifecycleBeanPostProcessor();
//     }
//
//     /**
//      * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,
//      * 并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)
//      * 即可实现此功能 *
//      * @return
//      */
//     @Bean
//     @DependsOn({"lifecycleBeanPostProcessor"})
//     public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//         DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//         advisorAutoProxyCreator.setProxyTargetClass(true);
//         return advisorAutoProxyCreator;
//     }
//
//     @Bean
//     public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//         AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//         authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//         return authorizationAttributeSourceAdvisor;
//     }
//
//     // public LoginFilter loginFilter() {
//     //     LoginFilter loginFilter = new LoginFilter();
//     //     loginFilter.setLoginUrl(loginUrl);
//     //     return loginFilter;
//     // }
//
// }
