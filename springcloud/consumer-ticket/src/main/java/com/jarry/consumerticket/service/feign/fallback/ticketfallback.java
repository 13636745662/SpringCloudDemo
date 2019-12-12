package com.jarry.consumerticket.service.feign.fallback;

import com.jarry.consumerticket.service.feign.TicketFeignClient;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: SpringCloud
 * @BelongsPackage: com.jarry.consumerticket.service.feign.service.feign.fallback
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-11 15:00
 */
@Component
public class ticketfallback implements TicketFeignClient {
    @Override
    public String getTicket(String name) {
        return "feign客户端启用hystrix，服务降级方法调用成功";
    }
}
