package com.jarry.consumerticket.service.feign;

import com.jarry.consumerticket.service.feign.fallback.ticketfallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.consumerticket.service.feign
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-11 17:30
 */
@FeignClient(value = "provider-ticket",fallback = ticketfallback.class)
public interface TicketFeignClient {

    @GetMapping("/ticket")
    String getTicket(String name);
}