package com.example.rabbitmq.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author ninan
 * @Description
 * @Date 2021/6/19
 **/
@Component
public class RabbitReceiverController {

    @RabbitListener(queues = "helloWorldqueue")
    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {

        try {
            Thread.sleep(10000*1);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("get msg1 success msg = "+msg);
        } catch (InterruptedException e) {

            channel.basicNack(message.getMessageProperties().getDeliveryTag(),
                    false, true);
            System.err.println("get msg1 failed msg = "+msg);
        }

    }


}
