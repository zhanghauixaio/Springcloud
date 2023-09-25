package org.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    private final static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping("/user/{userId}")
    public String getUserByUserId(@PathVariable("userId") String userId) {
        return "需要授权";
    }

    @GetMapping("/instance/{serviceId}")
    public String getInstance() {
        return "不需要授权";
    }
}
