package com.jarry.consumerticket.controller;

import com.jarry.consumerticket.service.feign.TicketFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @BelongsProject: SpringCloud
 * @BelongsPackage: com.jarry.consumerticket.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-10 16:29
 */
@RestController
public class UserController {
    @Autowired
    TicketFeignClient ticketFeignClient;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/buy")
//    @HystrixCommand(fallbackMethod = "buyTicketFallback")
    public String buyTicket(String name){
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name+"购买了"+s;
    }
    @PostMapping("/buy1")
//    @HystrixCommand(fallbackMethod = "buyTicketFallback")
    public String buy1(String name){
        String s = ticketFeignClient.getTicket(name) ;
        return name+"通过feign购买了"+s;
    }


    public String buyTicketFallback(String name){
        String s = "调用微服务失败❤，在此看到fallback方法被调用了❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤";
        return s;
    }
}
