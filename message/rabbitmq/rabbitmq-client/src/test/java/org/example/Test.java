package org.example;

import org.example.scheduler.SchedulerConfig;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        // 报错 refresh 多次
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SchedulerConfig.class);

        context.refresh();
    }
}
