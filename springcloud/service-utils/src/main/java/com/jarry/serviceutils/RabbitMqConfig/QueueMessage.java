package com.jarry.serviceutils.RabbitMqConfig;

import com.jarry.serviceutils.DTO.User;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.RabbitMqConfig
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-18 16:01
 */
public class QueueMessage {
    private User user;
    private long goodsId;

    public QueueMessage(User user, long goodsId) {
        this.user = user;
        this.goodsId = goodsId;
    }

    public User getUser() {return  user;}
    public void setUser(User user){this.user = user; }

    public long getGoodsId(){return goodsId;}

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
