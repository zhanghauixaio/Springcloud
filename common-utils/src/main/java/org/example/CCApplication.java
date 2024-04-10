package org.example;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CCApplication {
    public static void main(String[] args) {
        SpringApplication.run(CCApplication.class, args);
    }

    @GetMapping("test")
    @ApiOperation("test")
    public String get(){
        return "h";
    }
}
