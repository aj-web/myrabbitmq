package com.example.rabbitmq.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ninan
 * @Description
 * @Date  2021/6/19
 **/
@Component
public class MessageServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private static Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String exchange,String routingKey,Object msg) {
        //消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        //消息消费者确认收到消息后，手动ack回执
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        //发送消息
        //rabbitTemplate.convertAndSend(exchange,routingKey,msg);
        rabbitTemplate.send(exchange,(Message)msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("---- confirm ----ack="+ack+"  cause="+cause);
        log.info("correlationData -->"+correlationData.toString());
        if(ack){
            log.info("---- confirm ----ack==true  cause="+cause);
        }else{
            log.info("---- confirm ----ack==false  cause="+cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("---- returnedMessage ----replyCode="+replyCode+" replyText="+replyText+" ");
    }
}
