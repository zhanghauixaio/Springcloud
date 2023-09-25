package org.example;

import org.example.filter.ReFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.Filter;

@Controller
@SpringBootApplication(scanBasePackages = "org.example")
public class SentinelApplication implements CommandLineRunner {
    @Value("${filter.name}")
    String username;
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        System.out.println(username);
    }

    @ResponseBody
    @GetMapping("/h")
    public String test() {
        return "hhh";
    }

    @ResponseBody
    @GetMapping("/admin/12")
    public String test1() {
        return "admin/12";
    }

    @GetMapping("/toLogin")
    public String login() {
        return "login";
    }
}
