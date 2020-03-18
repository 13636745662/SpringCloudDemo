package com.jarry.serviceutils.testController;

import com.jarry.serviceutils.DTO.User;
import com.jarry.serviceutils.RabbitMqConfig.MQSender;
import com.jarry.serviceutils.RabbitMqConfig.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.testController
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-18 16:53
 */
@RestController
@Slf4j
public class MQTest {

    private static final int MAX_NUM = 1000;
    private static volatile long i = 1;
    @Autowired
    MQSender mqSender;

    @GetMapping("/TestMq")
    public String contextLoads() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (i<=MAX_NUM){
                    log.info("当前线程是:{},--输入至MQ队列的信息数字是:{}",Thread.currentThread().getName(),i++);//volite不能保证原子性
                    User user = new User();
                    QueueMessage msg = new QueueMessage(user,i);
                    mqSender.sendSeckillMessage(msg);
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        return "成功开启";
    }

}
