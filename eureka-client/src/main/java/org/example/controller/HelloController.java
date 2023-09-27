package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.example.feign.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.Future;

// @RibbonClient(name = "eureka-provider")
@RestController
public class HelloController {

    // @Resource
    // private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    /**
     * 测试 ribbon
     * @return
     */
    // @GetMapping("/hello")
    // public String hello(){
    //     return restTemplate.getForEntity("http://eureka-provider/hello",String.class).getBody();
    // }

    /**
     * 测试openfeign
     * @return
     */
    // @GetMapping("/getHello")
    // public String getHello(){
    //     return helloService.hello();
    // }

    /**
     * 测试openfeign
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    @GetMapping("/getHello1")
    public String getHello1(){
        return helloService.hello();
    }

    /**
     * 异步操作
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    @GetMapping("/async")
    public Future<String> async() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return helloService.hello();
            }
        };
    }
    public String error(){
        return "服务出错了************************";
    }
}
