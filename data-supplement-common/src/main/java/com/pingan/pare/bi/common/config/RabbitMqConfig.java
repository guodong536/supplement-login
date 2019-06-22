package com.pingan.pare.bi.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.util.Assert;

@Configuration
public class RabbitMqConfig {

    private String host="127.0.0.1";
    private int port=5672;
    private String username="guest";
    private String password= "guest";
    private boolean publisherConfirms=true;
    private String virtualHost="/";

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//因为要设置回调类，所以必须是prototype类型，多例而不是单例
    public ConnectionFactory connectionFactory() {
        Assert.hasLength(password, "rabbit password must not be empty");
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost(virtualHost);
        /**
         * 如果要进行消息回调，必须设置参数为true
         */
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template=new RabbitTemplate(connectionFactory());
        return template;
    }

    @Bean
    public Queue queue(){
        return new Queue("data-supplement",true);
    }
}
