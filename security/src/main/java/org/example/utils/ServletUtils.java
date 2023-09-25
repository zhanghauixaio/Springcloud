package org.example.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtils {
    public ServletRequestAttributes getAttributes(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
