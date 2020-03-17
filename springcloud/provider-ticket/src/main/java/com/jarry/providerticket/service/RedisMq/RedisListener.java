package com.jarry.providerticket.service.RedisMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.connection.Message;


/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.providerticket.service.RedisMq
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-16 14:37
 */
@Slf4j
@Component
public class RedisListener implements MessageListener {


    @Override
    public void onMessage(Message message,byte[] pattern){

        log.info("从消息通道={}监听到消息",new String(pattern));

        log.info("从消息通道={}监听到消息",new String(message.getChannel()));

        log.info("元消息={}",new String(message.getBody()));

        // 新建一个用于反序列化的对象，注意这里的对象要和前面配置的一样

        // 因为我前面设置的默认序列化方式为GenericJackson2JsonRedisSerializer

        // 所以这里的实现方式为GenericJackson2JsonRedisSerializer

        RedisSerializer serializer=new GenericJackson2JsonRedisSerializer();

//        log.debug("反序列化后的消息={}",serializer.deserialize(message.getBody()));

    }

}