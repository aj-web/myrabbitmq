package com.example.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ninan
 * @Description
 * @Date 2021/6/19
 **/
@Configuration
public class WorkConfig {

    @Bean
    public Queue workQueue() {
        return new Queue("workqueue");
    }

}
