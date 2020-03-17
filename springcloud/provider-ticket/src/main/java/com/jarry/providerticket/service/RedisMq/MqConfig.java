package com.jarry.providerticket.service.RedisMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.providerticket.service.RedisMq
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-16 14:35
 */
@Component
public class MqConfig{

    @Autowired
    private RedisListener redisListener;


    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);

        container.addMessageListener(redisListener, new PatternTopic("redis_channel"));

        return container;

    }
}
