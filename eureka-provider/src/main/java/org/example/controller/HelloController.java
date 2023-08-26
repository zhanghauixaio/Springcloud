package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        int stopTime = new Random().nextInt(3000);
        System.out.println("**********************" + stopTime + "***********************");
        Thread.sleep(stopTime);

        return "Hello World!";
    }
}
