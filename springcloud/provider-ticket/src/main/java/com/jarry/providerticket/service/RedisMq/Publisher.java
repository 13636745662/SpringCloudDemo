package com.jarry.providerticket.service.RedisMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.providerticket.service
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-16 14:33
 */
@Service
public class Publisher {
    @Autowired
    private RedisTemplate redisTemplate;

    public void publish(Object msg){
        redisTemplate.convertAndSend("redis_channel",msg);
    }
}
