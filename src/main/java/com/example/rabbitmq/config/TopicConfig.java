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
public class TopicConfig {
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicqueue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicqueue2");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindtopicBind1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("changsha.*");
    }

    @Bean
    public Binding bindtopicvBind2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("#.beijing");
    }
}
