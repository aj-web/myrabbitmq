package com.example.rabbitmq.controller;

import com.example.rabbitmq.serviceImpl.MessageServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Author ninan
 * @Description
 * @Date 2021/6/19
 **/
@RestController
public class RabbitSendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageServiceImpl messageService;

    @ApiOperation(value = "helloWorld发送接口", notes = "直接发送到队列")
    @GetMapping(value = "/helloWorldSend")
    public Object helloWorleSend(String message) {
        try {
            messageService.sendMessage("helloWorldqueue", "",new Message(message.getBytes("UTF-8")));
            return "message sended : " + message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "workSend发送接口", notes = "直接发送到队列")
    @GetMapping("/workSend")
    public Object workSend(String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        try {
            rabbitTemplate.send("workqueue", new Message(message.getBytes("UTF-8"), messageProperties));
            return "message sended : " + message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "fanoutSend发送接口", notes = "通过交换机发送到队列")
    @GetMapping("/fanoutSend")
    public Object fanoutSend(String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        try {
            for (int i = 0; i < 20; i++) {
                rabbitTemplate.send("fanoutExchange", "", new Message(message.getBytes("UTF-8"), messageProperties));
            }
            return "message sended : " + message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "directSend发送接口", notes = "通过交换机加上routingKey发送到队列")
    @GetMapping("/directSend")
    public Object directSend(String routingKey, String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        try {
            if (StringUtils.isEmpty(routingKey)) {
                routingKey = "china.changsha";
            }
            rabbitTemplate.send("directExchange", routingKey, new Message(message.getBytes("UTF-8"), messageProperties));
            return "message sended : routingKey >" + routingKey + ";message > " + message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "topicSend发送接口", notes = "通过交换机加上routingKey发送到队列")
    @GetMapping("/topicSend")
    public Object topicSend(String routingKey, String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        try {
            if (StringUtils.isEmpty(routingKey)) {
                routingKey = "changsha.kf";
            }
            rabbitTemplate.send("topicExchange", routingKey, new Message(message.getBytes("UTF-8"), messageProperties));
            return "message sended : routingKey >" + routingKey + ";message > " + message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
