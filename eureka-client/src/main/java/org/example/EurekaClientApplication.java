package org.example;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"org.example.feign"})
@SpringBootApplication(scanBasePackages = {"org.example"})
public class EurekaClientApplication {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    // TODO /actuator/hystrix.stream 一直ping,没有数据 hystrix-dashboard loading,没有数据
    // 此配置是为了服务监控而配置，与服务容错本身无关，
    // ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
    // 只要在自己的项目里配置上下面的servlet就可以了
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
