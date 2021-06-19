package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ninan
 * @Description
 * @Date 2021/6/19
 **/
@Configuration
public class DirectConfig {
    @Bean
    public Queue directQueue1() {
        return new Queue("directqueue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("directqueue2");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindDirectBind1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("china.changsha");
    }

    @Bean
    public Binding bindDirectBind2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("china.beijing");
    }
}
