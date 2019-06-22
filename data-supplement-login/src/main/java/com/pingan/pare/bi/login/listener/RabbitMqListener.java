package com.pingan.pare.bi.login.listener;

import com.rabbitmq.client.AMQP.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RabbitMqListener {

    @RabbitListener(queues = {"data-supplement1"})
    @RabbitHandler
    public void recevier(Channel channel, Message message){
        //json字符串，需要转化
        String data=new String(message.getBody());

        Map<String,Object> header=message.getMessageProperties().getHeaders();
        //content_type类型
        String contentType=message.getMessageProperties().getContentType();
        //correlation_id
        String correlationId=message.getMessageProperties().getCorrelationId();
        //队列
        String queue=message.getMessageProperties().getConsumerQueue();

        System.out.println("data:{}"+data+";;header:{}"+header+";;contentType:{}"+contentType+";;correlationId:{}"+correlationId);
    }

    @RabbitListener(queues = {"data-supplement"})
    @RabbitHandler
    public void recevier(String json){

        System.out.println("data:{}"+json);
    }
}
