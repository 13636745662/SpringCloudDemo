package com.jarry.serviceutils.RabbitMqConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.RabbitMqConfig
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-18 15:55
 */
@Configuration
public class MqConfig {
    static final String TEST_QUEUE = "test_queue";
    static final String QUEUE = "queue";

    @Bean
    public MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Queue queue(){ return new Queue(QUEUE,true); }
    @Bean
    public Queue queue2(){ return new Queue(TEST_QUEUE,true); }



}
