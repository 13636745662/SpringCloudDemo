package com.jarry.consumerticket.controller;

import com.jarry.consumerticket.service.feign.TicketFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @BelongsProject: SpringCloud
 * @BelongsPackage: com.jarry.consumerticket.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-12-10 16:29
 */
@Api("SwaggerDemo控制器")
@RestController
public class UserController {
    @Autowired
    TicketFeignClient ticketFeignClient;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation("(config分布式配置)测试连接数据库")
    @GetMapping("/jdbc")
    public String jdbc(){
        String sql = "SELECT * FROM article WHERE ID = 1";
        return jdbcTemplate.queryForMap(sql).toString();
    }
    @ApiOperation("通过resttemplate直接调用其它微服务模块")
    @PostMapping("/buy")
//    @HystrixCommand(fallbackMethod = "buyTicketFallback")
    public String buyTicket(String name){
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name+"购买了"+s;
    }

    @ApiOperation("通过eureka与feign客户端调用微服务模块")
    @PostMapping("/buy1")
//    @HystrixCommand(fallbackMethod = "buyTicketFallback")
    public String buy1(String name){
        String s = ticketFeignClient.getTicket(name) ;
        return name+"通过feign购买了"+s;
    }

//    @ApiOperation("fallback调用lalala")
    public String buyTicketFallback(String name){
        String s = "调用微服务失败❤，在此看到fallback方法被调用了❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤";
        return s;
    }
}
