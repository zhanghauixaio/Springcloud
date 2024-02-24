package org.example.scheduler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "org.example.scheduler")
public class SchedulerConfig implements InitializingBean {
    @Autowired
    private MyBean myBean;
    @PostConstruct
    public void init() {
        System.out.println("init");
    }
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(10);
    }
    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
    @Scheduled(fixedRate = 5000, initialDelay = 1000)
    public void send() throws InterruptedException {
        System.out.println("send");
        
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-------afterPropertiesSet----------");
    }

    // @Override
    // public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    //     System.out.println("beforeInitialization----------------");
    //     return bean;
    // }
    //
    // @Override
    // public int getOrder() {
    //     return Ordered.LOWEST_PRECEDENCE;
    // }
}
