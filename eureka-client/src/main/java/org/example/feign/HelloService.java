package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "eureka-provider")
public interface HelloService {
    @GetMapping("/hello")
    @ResponseBody
    public String hello();
}
