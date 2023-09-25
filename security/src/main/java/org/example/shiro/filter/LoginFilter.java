package org.example.shiro.filter;

// import org.apache.shiro.web.filter.AccessControlFilter;
// import org.apache.shiro.web.util.WebUtils;
// import org.springframework.beans.factory.annotation.Value;
//
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import java.io.IOException;
//
// public class LoginFilter extends AccessControlFilter {
//     @Value("${shiro.user.loginUrl}")
//     private String loginUrl;
//
//     @Override
//     protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
//         return false;
//     }
//
//     @Override
//     protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//         saveRequestAndRedirectToLogin(servletRequest, servletResponse);
//         return false;
//     }
//
//     @Override
//     protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
//         WebUtils.issueRedirect(request, response, loginUrl); // 空指针异常
//     }
// }
