package com.jarry.serviceutils.RabbitMqConfig;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.RabbitMqConfig
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-18 16:05
 */
@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);
    @Autowired
    AmqpTemplate amqpTemplate;

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public  void sendSeckillMessage(QueueMessage message){
        String msg = JSON.toJSONString(message);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MqConfig.TEST_QUEUE,msg);
    }
}
