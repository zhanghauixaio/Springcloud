package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class FirstController {
    @Value("${name}")
    String name;

    @Value("${sex}")
    String sex;

    @GetMapping("/user")
    public String getUser() {
        return "姓名：" + name + " 性别：" + sex;
    }
}
