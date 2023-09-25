// package org.example.security.config;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//         InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> detailsManagerConfigurer = auth.inMemoryAuthentication().passwordEncoder(passwordEncoder);
//         detailsManagerConfigurer.withUser("admin").password(passwordEncoder.encode("123456")).roles("vip1");
//
//     }
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//             http.authorizeRequests()
//                 .antMatchers("/admin/**").hasRole("vip1")
//                 .anyRequest().permitAll()
//                 .and().formLogin()
//                 .and().httpBasic();
//     }
//
//     public static void main(String[] args) {
//         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//         System.out.println(passwordEncoder.encode("123456")); // $2a$10$xXFtNOxGlWI/J8W32k1cnOK/hLi9pSgvOU3LjpeSLpsqCMdu02GuK
//     }
// }
