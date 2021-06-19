package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ninan
 * @Description
 * @Date 2021/6/19
 **/
@Configuration
public class FanoutConfig {

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutqueue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutqueue2");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindQ1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindQ2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
