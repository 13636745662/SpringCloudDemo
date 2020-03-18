package com.jarry.serviceutils.RabbitMqConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.RabbitMqConfig
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-18 16:12
 */
@Service
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @RabbitListener(queues=MqConfig.TEST_QUEUE)
    public void receive(String message) {
        log.info("receive message:"+message);
    }
}
