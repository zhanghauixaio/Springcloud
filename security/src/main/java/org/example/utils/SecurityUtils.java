package org.example.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

public class SecurityUtils {

    public void getSession() {

    }
    public Principal getPrincipal() {
        return (Principal) getAuthentication().getPrincipal();
    }
    public Authentication getAuthentication() {
       return SecurityContextHolder.getContext().getAuthentication();
    }
}
